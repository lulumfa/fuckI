package Leet;

// the parameters could be doulbe and we need the epsilon to consider the error. But this data structure fails to consider the slope with infinite

public class TwoLinesIntersect {
	public final double EPSILON = 0.000001; 
	private class Line
	{
		public double slope;
		public double yIntersection;
		public Line(int slope, int yIntersection){
			this.slope = slope;
			this.yIntersection = yIntersection;
		}
		
		public boolean checkIntersection(Line a, Line b){
			if(a==null || b==null) return false;
			return ((Math.abs(a.slope-b.slope)> EPSILON) || 
					(Math.abs(a.yIntersection-b.yIntersection)< EPSILON));
		}
	}
	
}
