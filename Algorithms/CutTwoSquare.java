package Cracking;

public class CutTwoSquare {
	public static final double EPSILON = 0.000001;
	class Square
	{
		Point leftTopCorner;
		double length;
		double height;
		Square(Point corner, double len, double hei)
		{
			leftTopCorner = corner;
			length = len;
			height = hei;
		}
		public Point getCenter()
		{
			if(length==0||height==0) return null;
			Point center = new Point(leftTopCorner.x+length/2,leftTopCorner.y-height/2 );
			return center;
		}
	}
	class Point
	{
		double x;
		double y;
		Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
	}
	
	class Line
	{
		double slope;
		double yIntersection;
		double x;
		Line(double slope, double y)
		{
			this.slope = slope;
			yIntersection = y;
		}
		Line(double x)
		{
			this.x = x;
		}
	}
	class VerticalLine extends Line
	{
		double x;
		VerticalLine(double x)
		{
			super(x);
		}
	}
	public Line cutTwoSquare(Square a, Square b)
	{
		
		if(a==null || b==null||a.length<=0||a.height<=0||b.length<=0||b.height<=0) return null;
		Point centerA = a.getCenter();
		Point centerB = b.getCenter();
		// the same two points can just use one result as the vertical one
		if(Math.abs(centerA.x-centerB.x)<EPSILON) return new VerticalLine(centerA.x);
		double slope  = (centerA.y-centerB.y)/(centerA.x=centerB.x);
		double yIntersection = centerA.y-slope*centerA.x;
		return new Line(slope, yIntersection);
	}
}
