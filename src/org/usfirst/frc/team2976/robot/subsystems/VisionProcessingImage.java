package org.usfirst.frc.team2976.robot.subsystems;

/*
 * README
 * How to use this class: 
 * - Must have Target in package or imported
 * - set I/O
 * - Run initialize() ONLY ONCE
 * - repeatedly call initDefaultCommand to constantly refresh current Target
 * - to get target info/distance call getCurrentTarget() and assorted object methods
 * - outputImage() is useful to see where the target is in the picture
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

public class VisionProcessingImage{

private int xRes;
private int yRes;
private double horizontalVAngle;
private double verticalVAngle;
private double aspectRatio;
private String input;
private String output;
private ArrayList<MatOfPoint> contours;
private Mat hierarchy;
private ArrayList<Target> targets;
private Target currentTarget;

private boolean switchControl; //from field management system, drive autonomous should determine correct target

	public VisionProcessingImage(int xRes, int yRes, double horizontalVAngle, double verticalVAngle, double aspectRatio) {
		this.xRes = xRes;
		this.yRes = yRes;
		this.horizontalVAngle = horizontalVAngle;
		this.verticalVAngle = verticalVAngle;
		this.aspectRatio = aspectRatio;
	}
	
	public VisionProcessingImage() {
		
	}
	
	public void initialize() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public void initDefaultCommand() {
		
		Mat mat = Imgcodecs.imread(input);
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);  //BlueGreenRed to HueSaturationValue
		Core.inRange(mat, new Scalar(0, 0, 250), new Scalar(180, 200, 255), mat); //specific HSV range between the Scalars, use GRIP 
		
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
				if(boundingRects.get(i).height > 30 && boundingRects.get(j).height > 30) { //filter out tiny rects that couldn't be viable target
					if(boundingRects.get(i).width > 4 && boundingRects.get(j).width > 4) {
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
			if(targets.get(i).getEdgeRatio() > 2.5 && targets.get(i).getEdgeRatio() < 3.5) {
				if(targets.get(i).getHeightRatio() < 0.5 && targets.get(i).getWidthRatio() < 0.5) {
					if(targets.get(i).getDimensionRatio() < lowestSelectedRatio) {
						lowestSelectedRatio = targets.get(i).getDimensionRatio();
						targetIndex = i;
					}
				}
			}
		}
		currentTarget = targets.get(targetIndex);
	}
	
	public void outputImage(String sourceLocation, String outputLocation) {
		Mat mat = Imgcodecs.imread(sourceLocation);
		Scalar color = new Scalar(255, 0, 255); //color picking depends on source image
		Imgproc.rectangle(mat, new Point(currentTarget.getLRect().x, currentTarget.getLRect().y), 
				new Point(currentTarget.getRRect().x + currentTarget.getRRect().width, currentTarget.getRRect().y + currentTarget.getRRect().height), color, 2);
		Imgproc.line(mat, new Point(getXRes()/2 - 50, yRes/2), new Point(getXRes()/2 + 50, yRes/2), color, 2);
		Imgproc.line(mat, new Point(getXRes()/2, yRes/2 - 50), new Point(getXRes()/2, yRes/2 + 50), color, 2);
		Imgcodecs.imwrite(outputLocation, mat);
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String getOutput() {
		return output;
	}
	
	public String getInput() {
		return input;
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
