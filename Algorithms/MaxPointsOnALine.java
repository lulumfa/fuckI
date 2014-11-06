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
        if(points==null || points.length==0) return 0;
        if(points.length==1) return 1;
        
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        Point a, b;
        int max = 1;
        int temp = 1;
        double k;
        for(int i=0; i< points.length; i++) {
            map.clear();
            temp = 1;
            a = points[i];
            for(int j = 0; j< points.length; j++) {
                b = points[j];
                if(i==j) {
                    continue;
                }
                if(a.x==b.x && a.y==b.y) {
                    temp++;
                } else {
                    
                    if(a.x==b.x) {
                        k = Double.MAX_VALUE;
                    } else {
                        k = ((double)b.y-(double)a.y)/((double)a.x-(double)b.x);
                    }
                    if(map.containsKey(k)) {
                        map.put(k, map.get(k)+1);
                    }else {
                        map.put(k, 1);
                    }
                }
            }
            int count = 0;
            for(Integer num : map.values()) {
                if(num>count) count = num;
            }
            count += temp;
            max = Math.max(count, max);
        }
        return max;
    }
}
