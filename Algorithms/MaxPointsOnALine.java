/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        if(points==null) return 0;
        int max = 0;
        for(int i = 0; i<points.length; i++) {
            Point a = points[i];
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            int dup = 1;
            int maxMap = 0;
            for(int j = i+1; j<points.length; j++) {
                Point b = points[j];
                if(a.x==b.x && a.y==b.y) dup++;
                else {
                    double slope;
                    if(a.x==b.x) slope = Double.MAX_VALUE;
                    else if(a.y==b.y) slope = 0.0;
                    else slope = (double)(b.y-a.y)/(double)(b.x-a.x);
                    if(map.containsKey(slope)) {
                        map.put(slope, map.get(slope)+1);
                    } else {
                        map.put(slope, 1);
                    }
                    maxMap = Math.max(maxMap, map.get(slope));
                }
            }
            max = Math.max(max, dup+maxMap);
        }
        return max;
    }
}
