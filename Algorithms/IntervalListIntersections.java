// cleaner one
// O(M+N) my own, space (M+N)
class Solution {
  public int[][] intervalIntersection(int[][] A, int[][] B) {
    List<int[]> ans = new ArrayList();
    int i = 0, j = 0;

    while (i < A.length && j < B.length) {
      // Let's check if A[i] intersects B[j].
      // lo - the startpoint of the intersection
      // hi - the endpoint of the intersection
      int lo = Math.max(A[i][0], B[j][0]);
      int hi = Math.min(A[i][1], B[j][1]);
      if (lo <= hi)
        ans.add(new int[]{lo, hi});

      // Remove the interval with the smallest endpoint
      if (A[i][1] < B[j][1])
        i++;
      else
        j++;
    }

    return ans.toArray(new int[ans.size()][]);
  }
}

// O(M+N) my own, space (M+N)
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return new int[0][0];
        
        int i = 0, j = 0;
        List<int[]> list = new ArrayList<int[]>();
        
        while(i < A.length && j < B.length) {
            int[] a = A[i], b = B[j];

            if (a[0] > b[1]) {
                j++;
            } else if (b[0] > a[1]) {
                i++;
            } else {
                list.add(new int[] {Math.max(a[0], b[0]), Math.min(a[1], b[1])});
                
                if (a[1] < b[1]) i++;
                else j++;
            }
        }
        
        int[][] res = new int[list.size()][2];
        i = 0;
        for (int[] tuple : list) {
            res[i++] = tuple;
        }
        return res;
    }
}

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
