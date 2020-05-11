package ab;

/*
# You're in a ski tournament, where you ski from top of the mountain to one of the
# finish checkpoints at the bottom. There are multiple routes with checkpoints
# (each checkpoint has an associated point with it). And your total score =
# (points from all checkpoints you visited - time of your travel).

# Given: A List of lists, that consists [start_cp, end_cp, time_to_travel]
#        A list of lists, that consists [cp, point]
# Goal: Find the maximum score that you can collect during the tournament and print
# out the optimal path.


#                  START[0]
#                   / | \
#                  5  6  10
#                 /   |   \
#              A[24] B[3] C[10]
#                 \   |   /|
#                  4  5  6 5
#                   \ | /  |
#                    D[7] E[24]
#                      \   |
#                       3  1
#                        \ |
#                         F[3]
#                         / \
#                        5  10
#                       /     \
#                   END_1[4]  END_2[7]
*/



/*
// Java
String[][] travelTime = {
    {"START", "A", "5"},
    {"START", "B", "6"},
    {"START", "C", "10"},
    {"A", "D", "4"},
    {"B", "D", "5"},
    {"C", "D", "6"},
    {"C", "E", "5"},
    {"D", "F", "3"},
    {"E", "F", "1"},
    {"F", "END_1", "5"},
    {"F", "END_2", "10"},
};
String[][] points = {
    {"START", "0"},
    {"A", "24"},
    {"B", "3"},
    {"C", "10"},
    {"D", "7"},
    {"E", "24"},
    {"F", "3"},
    {"END_1", "4"},
    {"END_2", "7"},
};
*/

import java.util.*;

public class SkiScore {

    private static final String END_PREFIX = "END_";
    private static final String START = "START";

    public static void main(String[] args) {
        String[][] travelTime = {
                {"START", "A", "5"},
                {"START", "B", "6"},
                {"START", "C", "10"},
                {"A", "D", "4"},
                {"B", "D", "5"},
                {"C", "D", "6"},
                {"C", "E", "5"},
                {"D", "F", "3"},
                {"E", "F", "1"},
                {"F", "END_1", "5"},
                {"F", "END_2", "10"},
        };
        String[][] points = {
                {"START", "0"},
                {"A", "24"},
                {"B", "3"},
                {"C", "10"},
                {"D", "7"},
                {"E", "24"},
                {"F", "3"},
                {"END_1", "4"},
                {"END_2", "7"},
        };

        SkiScore skiScore = new SkiScore();

        Result result = skiScore.getMaxSkiScoreAndPath(travelTime, points);

        System.out.println(result.score);
        System.out.println(result.path.toString());
    }

    public Result getMaxSkiScoreAndPath (String[][] travelTime, String[][] points) {
        Result result = new Result(0, new ArrayList<>());
        if (travelTime == null || points == null) return result;

        Map<String, Stop> map = new HashMap<>();


        // building the graph (vertices and edges)
        for (String[] travel : travelTime) {
            if (travel == null || travel.length != 3) continue;

            String from = travel[0], to = travel[1];
            int time = Integer.valueOf(travel[2]);

            if (!map.containsKey(from)) map.put(from, new Stop(from));
            if (!map.containsKey(to)) map.put(to, new Stop(to));
            map.get(from).next.put(map.get(to), time);
            map.get(to).inbound++;
        }

        // get the points map

        Map<String, Integer> stopPoints = new HashMap<>();
        for (String[] point : points) {
            stopPoints.put(point[0], Integer.valueOf(point[1]));
        }

        // topological sequence
        Queue<Stop> queue = new LinkedList<>();
        for (Stop stop : map.values()) {
            // assuming there will be only one which is the START point
            if (stop.inbound == 0) queue.offer(stop);
        }

        int maxScore = 0;
        String maxlabel = null;

        Map<String, String> parent = new HashMap<>();
        for (String label : map.keySet()) {
            parent.put(label, label);
        }

        while (!queue.isEmpty()) {
            Stop cur = queue.poll();
            if (isEnd(cur.label)) {
                if (cur.score > maxScore) {
                    maxScore = cur.score;
                    maxlabel = cur.label;
                }
                continue;
            }
            for (Map.Entry<Stop, Integer> entry: cur.next.entrySet()) {
                int newScore = cur.score - entry.getValue() + stopPoints.get(entry.getKey().label);
                if (newScore > entry.getKey().score) {
                    entry.getKey().score = newScore;
                    parent.put(entry.getKey().label, cur.label);
                }

                entry.getKey().inbound--;
                if (entry.getKey().inbound == 0) {
                    queue.offer(entry.getKey());
                }
            }
        }

        if (maxlabel == null) return result;

        String id = maxlabel;

        List<String> path = new ArrayList<>();
        while (!id.equals(START)) {
            path.add(id);
            id = parent.get(id);
        }

        path.add(START);

        Collections.reverse(path);

        return new Result(maxScore, path);
    }

    private boolean isEnd(String label) {
        return label != null && label.contains(END_PREFIX);
    }

}

class Stop {
    String label;
    int inbound;
    Map<Stop, Integer> next;
    int score;

    public Stop (String label) {
        this.label = label;
        this.inbound = 0;
        this.score = 0;
        this.next = new HashMap<>();
    }
}

class Result {
    int score;
    List<String> path;

    public Result(int score, List<String> path) {
        this.score = score;
        this.path = path;
    }
}
