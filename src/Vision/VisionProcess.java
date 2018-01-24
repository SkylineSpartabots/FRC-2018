package Vision;

/*
 * README
 * How to use this class: 
 * - Must have Target in package or imported
 * - Call initialize() ONLY ONCE
 * - Call targeting() with param to constantly refresh current Target 
 * - Getting target info/distance call getCurrentTarget() and assorted object methods
 * - getOutput() is useful to see where the target is in the picture
 */
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class VisionProcess{

private int xRes;
private int yRes;
private double horizontalVAngle;
private double verticalVAngle;
private double aspectRatio;

private Mat output;
private ArrayList<MatOfPoint> contours;
private Mat hierarchy;
private ArrayList<Target> targets;
private Target currentTarget;

private boolean switchControl; //from field management system, drive autonomous should determine correct target

	public VisionProcess(int xRes, int yRes, double horizontalVAngle, double verticalVAngle, double aspectRatio) {
		this.xRes = xRes;
		this.yRes = yRes;
		this.horizontalVAngle = horizontalVAngle;
		this.verticalVAngle = verticalVAngle;
		this.aspectRatio = aspectRatio;
	}
	
	public void initialize() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public void targeting(Mat input) {
		
		Mat mat = input;
		output = input;
		
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);  //BlueGreenRed to HueSaturationValue
		Core.inRange(mat, new Scalar(40, 0, 250), new Scalar(90, 220, 255), mat); //specific HSV range between the Scalars, use GRIP 
		
		contours = new ArrayList<MatOfPoint>();
		hierarchy = new Mat(); //empty
		targets = new ArrayList<Target>();
		
		Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE); //Might want to tune this a little
		
		createTargets();
		rateTargets();	
	}
	
	public void createTargets() {
		ArrayList<Rect> boundingRects = new ArrayList<Rect>();
		for(int i = 0; i < contours.size(); i++) {
			boundingRects.add(Imgproc.boundingRect(contours.get(i))); 
		}
		for(int i = 0; i < boundingRects.size(); i++) {
			for(int j = i + 1; j < boundingRects.size(); j++) {
				if(boundingRects.get(i).height > 37 && boundingRects.get(j).height > 37) { //filter out tiny rects that couldn't be viable target
					if(boundingRects.get(i).width > 5 && boundingRects.get(j).width > 5) {
						if(boundingRects.get(i).x > boundingRects.get(j).x) { //i is right of j
							targets.add(new Target(boundingRects.get(j), boundingRects.get(i), xRes, yRes, horizontalVAngle, verticalVAngle));
						}else { //i is left of j
							targets.add(new Target(boundingRects.get(i), boundingRects.get(j), xRes, yRes, horizontalVAngle, verticalVAngle));
						}
					}
				}
				
			}
		}
	}
	
	public void rateTargets() {
		double lowestSelectedRatio = 1000000000;
		int targetIndex = 0;

		for(int i = 0; i < targets.size(); i++) { //here's the real "algorithm"
			if(targets.get(i).getSpaceRatio() > 0) {
				if(targets.get(i).getDimensionRatio() < lowestSelectedRatio) {
					lowestSelectedRatio = targets.get(i).getDimensionRatio();
					targetIndex = i;
				}
			}
		}
		currentTarget = targets.get(targetIndex);
	}
	
	public Mat getOutput(Mat input) {
		
		this.output = input;
		Scalar color1 = new Scalar(0, 255, 0); //color picking depends on source image
		Scalar color2 = new Scalar(255, 0, 0);
		
		Imgproc.rectangle(this.output, //overall target
				new Point(currentTarget.getLRect().x, currentTarget.getLRect().y), 
				new Point(currentTarget.getRRect().x + currentTarget.getRRect().width, currentTarget.getRRect().y + currentTarget.getRRect().height), 
				color1, 2);
		Imgproc.rectangle(this.output, 
				new Point(currentTarget.getLRect().x, currentTarget.getLRect().y), 
				new Point(currentTarget.getLRect().x + currentTarget.getLRect().width, currentTarget.getLRect().y + currentTarget.getLRect().height),
				color2,2);
		Imgproc.rectangle(this.output, //left tape
				new Point(currentTarget.getRRect().x, currentTarget.getRRect().y), 
				new Point(currentTarget.getRRect().x + currentTarget.getRRect().width, currentTarget.getRRect().y + currentTarget.getRRect().height),
				color2,2); 
		
		//crosshairs
		Imgproc.line(this.output, new Point(getXRes()/2 - 40, yRes/2), new Point(getXRes()/2 + 40, yRes/2), color1, 2);
		Imgproc.line(this.output, new Point(getXRes()/2, yRes/2 - 40), new Point(getXRes()/2, yRes/2 + 40), color1, 2);
		return output;
	}
	
	public int getXRes() {
		return xRes;
	}
	
	public ArrayList<MatOfPoint> getContours(){
		return contours;
	}
	
	public Mat getHierarchy() {
		return hierarchy;
	}
	
	public ArrayList<Rect> boundingRects(){
		return boundingRects();
	}
	
	public Target getCurrentTarget() {
		return currentTarget;
	}
	
	public ArrayList<Target> getTargets(){
		return targets;
	}
}
