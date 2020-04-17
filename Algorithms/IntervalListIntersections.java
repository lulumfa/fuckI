// On(logn), not accepted.., space (m+n)
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return new int[0][0];
        
        Point[] points = new Point[A.length * 2 + B.length * 2];
        int i = 0;
        for (int[] interval : A) {
            points[i++] = new Point(interval[0], true);
            points[i++] = new Point(interval[1], false);
        }
        for (int[] interval : B) {
            points[i++] = new Point(interval[0], true);
            points[i++] = new Point(interval[1], false);
        }
        
        Arrays.sort(points, new Comparator<Point>(){
           @Override
            public int compare(Point a, Point b) {
                if (a.val == b.val) return a.isLeft ? -1 : 1;
                return a.val - b.val;
            }
        });
        int leftCount = 0;
        int left = 0;
        List<int[]> list = new ArrayList<int[]>();
        
        for(Point p : points) {
            if (p.isLeft) {
                leftCount++;
                left = p.val;
            } else {
                if (leftCount == 2) list.add(new int[] {left, p.val});
                leftCount--;
            }
        }
        
        int[][] res = new int[list.size()][2];
        
        i = 0;
        for (int[] interval : list) {
            res[i++] = interval;
        } 
        return res;
    }
}

class Point {
    int val;
    boolean isLeft;
    
    public Point(int val, boolean isLeft) {
        this.val = val;
        this.isLeft = isLeft;
    }
}
