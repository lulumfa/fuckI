// topo sort and no more than that, O(E + V), backtrace is just O(V)
// using topo sort to avoid dup paths with normal BFS
// reference: https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/

package airbnb;

import java.util.*;

public class SkiScore {

    public static void main(String[] args) {
        String[] edges = new String[] {
            "A,B,1",
            "A,C,1",
            "B,D,1",
            "C,E,1",
            "E,F,1",
            "F,D,1",
            "D,G,1",
        };

        Map<String, Integer> points = new HashMap<>();
        points.put("A", 2);
        points.put("B", 3);
        points.put("C", 2);
        points.put("D", 1);
        points.put("E", 3);
        points.put("F", 4);
        points.put("G", 5);

        SkiScore skiScore = new SkiScore();

        System.out.println(skiScore.calulateScore(edges, points, "A", new HashSet<>(Arrays.asList("G"))));
    }

    public Result calulateScore(String[] edges, Map<String, Integer> points, String src, Set<String> dsts) {
        if (edges == null || edges.length == 0
                || points == null || points.size() == 0 || src == null || !points.containsKey(src) || dsts == null || dsts.size() == 0) return null;
        Map<String, CheckPoint> map = new HashMap<>();

        for (String edge : edges) {
            if (edge == null) continue;
            String[] segments = edge.split(",");
            if (segments.length != 3) continue;
            String s = segments[0], t = segments[1];
            if (!points.containsKey(s) || !points.containsKey(t)) continue;
            int point = Integer.valueOf(segments[2]);

            if (!map.containsKey(s)) {
                map.put(s, new CheckPoint(s, points.get(s)));
            }
            if (!map.containsKey(t)) {
                map.put(t, new CheckPoint(t, points.get(t)));
            }
            map.get(s).next.put(map.get(t), point);
            map.get(t).inbound++;
        }

        Map<String, String> parent = new HashMap<>();
        for (String label : points.keySet()) {
            parent.put(label, label);
        }

        Queue<CheckPoint> queue = new LinkedList<>();

        queue.offer(map.get(src));

        while(!queue.isEmpty()) {
            CheckPoint cur = queue.poll();

            for (CheckPoint checkPoint : cur.next.keySet()) {
                checkPoint.inbound--;
                if (checkPoint.inbound == 0) queue.offer(checkPoint);
                int newScore = cur.score + cur.next.get(checkPoint) + 2 * checkPoint.point;
                if (newScore > checkPoint.score) {
                    checkPoint.score = newScore;
                    parent.put(checkPoint.label, cur.label);
                }
            }
        }

        int res = 0;
        String id = null;

        for (String dst : dsts) {
            if (map.get(dst).score > res) {
                res = map.get(dst).score;
                id = dst;
            }
        }

        if (id == null || res == 0) return null;

        List<String> path = new ArrayList<>();

        while (!id.equals(src)) {
            path.add(id);
            id = parent.get(id);
        }

        path.add(src);

        Collections.reverse(path);

        return new Result(res, path);
    }
}

class Result {
    int score;
    List<String> path;

    public Result(int score, List<String> path) {
        this.score = score;
        this.path = path;
    }

    @Override
    public String toString() {
        return score + "\n" + path.toString();
    }
}

class CheckPoint {
    String label;
    int score;
    int point;
    int inbound;
    Map<CheckPoint, Integer> next;

    public CheckPoint(String label, int point) {
        this.label = label;
        this.score = 0;
        this.point = point;
        next = new HashMap<>();
    }
}
