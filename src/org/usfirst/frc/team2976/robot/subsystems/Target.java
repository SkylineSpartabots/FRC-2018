package org.usfirst.frc.team2976.robot.subsystems;



import org.opencv.*;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Target {
	
private static int ID = 1; 
private static int xRes; //CHECK THESE WITH CAMERA USED
private static int yRes;
private static double horizontalVAngle;
private static double verticalVAngle; //radian
private Rect lRect;
private Rect rRect;

	public Target(Rect lRect, Rect rRect, int xRes, int yRes, double horizontalVAngle, double verticalVAngle) {
		ID++;
		this.lRect = lRect;
		this.rRect=  rRect;
		this.xRes = xRes;
		this.yRes = yRes;
		this.horizontalVAngle = horizontalVAngle;
		this.verticalVAngle = verticalVAngle;
	}
	
	public int getID() {
		return ID;
	}
	
	public Rect getLRect() {
		return lRect;
	}
	
	public Rect getRRect() {
		return rRect;
	}
	
	public double getWidthRatio() {
		double widthRatio = 0;
		if(lRect.width >= rRect.width) {
			widthRatio = Math.abs(lRect.width - rRect.width)/rRect.width;
		}else {
			widthRatio = Math.abs(lRect.width - rRect.width)/lRect.width;
		}
		return widthRatio;
	}
	
	public double getHeightRatio() {
		double heightRatio = 0;
		if(lRect.height >= rRect.width) {
			heightRatio = (double)Math.abs(lRect.height - rRect.height)/rRect.height;
		}else {
			 heightRatio = (double)Math.abs(lRect.height - rRect.height)/lRect.height;
			
		}
		return heightRatio;
	}
	
	public double getDimensionRatio() {
		double dimensionRatio = Math.abs((15/2 - lRect.height/rRect.width)) + Math.abs((15/2 - rRect.height/lRect.width)); 
		//dimension ratio cross-match
		return dimensionRatio;
	}
	
	public double getEdgeRatio() {
		double edgeRatio = distanceF(new Point(lRect.x, lRect.y), new Point(rRect.x, rRect.y))/lRect.width; 
		//The distance between the two left edges should be small relative to width
		return edgeRatio;
	}
	
	public double getTrigRatio() {
		
		double trigRatio = 0.5 - Math.tanh(lRect.height/(lRect.width + rRect.width 
					           + (distanceF(new Point(lRect.x, lRect.y), new Point(rRect.x, rRect.y)) + rRect.width)));
		return trigRatio;
	}
	/*ratios:
	target is 15.3 inches tall
	target is 2 inches wide
	Target ft/Target pixels = FOV ft/ FOV pixels
	FOV Yft =  2 * w = 2 * d * tan theta
	where theta = 1/2 horizontalVAngle
	
	d = target ft * FOV pixels / (2 * target pixels * tan theta)
	*/
	public double getXDistance() { //right relative to robot is positive

		double pixelOffset = ((lRect.x + rRect.x + rRect.width)/2 - xRes/2); //positive is right
		double xDistance = (2.0/12.0 * pixelOffset / ((lRect.width + rRect.width)/2.0));
		return xDistance;
	}
	
	public double getYDistance() { //always positive
		double yDistance = 15.3/12 * yRes/(2 * lRect.height* Math.tan(verticalVAngle/2)) ;
		return yDistance;
	}
	
	public String toString() {
		String s = "Forward Distance, Sideways Distance: " + getYDistance() +  ", " + getXDistance();
		return s;	
	}
	
	public double distanceF(Point a, Point b) {
		double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
		return distance;
	}
}
