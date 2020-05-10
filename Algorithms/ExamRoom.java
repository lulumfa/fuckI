// O(logn) for seat and leave, space (n), n = # of people sitting

// http://bookshadow.com/weblog/2018/06/17/leetcode-exam-room/

// TreeSet

// 将每两个座位之间的位置视为“区间”

// 利用一个TreeSet维护这样的区间，记为pq

// 用另一个TreeSet维护当前被占用的座位标号，记为seats。

// 对于leave操作，seats可以用O(log n)的代价找到某座位相邻的座位。并将两个区间合二为一。

// 对于seat操作，可以通过pq获取当前的最大区间，将区间一分为二

class ExamRoom {

    private int N;
    private TreeSet<Integer> seats = new TreeSet<>();
    private TreeSet<Point> pq = new TreeSet<>(
            (Point p1, Point p2) -> {
                int d1 = getDistance(p1);
                int d2 = getDistance(p2);
                if (d1 == d2) {
                    return p1.x - p2.x; 
                }
                return d2 - d1;
            });

    public ExamRoom(int N) {
        this.N = N;
        seats.add(-1);
        seats.add(N);
        pq.add(new Point(-1, N));
    }
    
    private int getDistance(Point p) {
        int mid = getMid(p.x, p.y);
        if (p.x < 0) {
            return p.y - mid;
        } else if (p.y == this.N) {
            return mid - p.x;            
        }
        return Math.min(p.y - mid, mid - p.x);
    }
    
    private int getMid(int left, int right) {
        if (left < 0) return 0;
        if (right == this.N) return this.N - 1;
        return (left + right) / 2;
    }
    
    public int seat() {
        Point p = pq.pollFirst();
        int left = p.x, right = p.y;
        int mid = getMid(left, right);
        seats.add(mid);
        pq.add(new Point(left, mid));
        pq.add(new Point(mid, right));
        return mid;
    }
    
    public void leave(int p) {
        int left = seats.lower(p);
        int right = seats.higher(p);
        seats.remove(p);
        pq.remove(new Point(left, p));
        pq.remove(new Point(p, right));
        pq.add(new Point(left, right));
    }
}

class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
