// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=624815&ctid=230577
// dp 4D matrix lol, 2D for all x, y combination, 3D for various result, 4D for list of steps
// O(m*n * k * (m+n)) if m == n, then O(n^3 *k) k is the # of score possibilities, like 2, 5, 6, then k = 3
// space O(m*n * (m+n))?  TODO


// // If only ask how many results, then we can do 2D with initializing count = 0, and dp[i][j] = sum (dp[i-score_x][y], dp[i][j - score_k] + ..) 
// // and we should not need to worry about dedup since the there wont be dup as the routes would different from each step 

// rolling array wont help here to reduce space because it is not always just depending the previous index

// dfs + memo would work as well, can try it later on, complexity should be similar


package snap;

import java.util.ArrayList;
import java.util.List;

public class GameScoreCombination {

  public static void main(String[] args) {
    int a = 7, b = 5;

    int[] possibleScores = {2, 5, 7};

    GameScoreCombination gameScoreCombination = new GameScoreCombination();
    List<List<Score>> result = gameScoreCombination.findCombinationOfScores(a, b, possibleScores);

    int i = 1;
    for (List<Score> game : result) {
      System.out.println(i++ + " combination");
      int j = 0;
      for(Score score : game) {
        // skipe the initial state (0, 0) for readability
        if (j++ == 0) continue;
        System.out.println(score);
      }
      System.out.println();
    }

  }

  // dp[i][j] sum of (dp[i-score_i][j] + dp[i-score_j] + ...)
  public List<List<Score>> findCombinationOfScores(int a, int b, int[] possibleScores) {
    if (a < 0 || b < 0 || possibleScores == null || possibleScores.length == 0) return null;

    List<List<List<List<Score>>>> dp = new ArrayList<>();

    List<List<Score>> startScores = new ArrayList<List<Score>>(){{
      add(new ArrayList<Score>(){{
        add(new Score(0, 0));
      }});
    }};

    // initialize the state when x = 0, and y = 0, 1, 2,3,.., b
    List<List<List<Score>>> xStart = new ArrayList<>();
    xStart.add(startScores);

    for (int i = 1; i <= b; i++) {
      List<List<Score>> scores = new ArrayList<>();

      for (int score : possibleScores) {
        if (i - score >= 0) {
          for (List<Score> preScores : xStart.get(i-score)) {
            List<Score> temp = new ArrayList<>(preScores);
            temp.add(new Score(0, score));
            scores.add(temp);
          }
        }
      }

      xStart.add(scores);
    }


    dp.add(xStart);

    for (int i = 1; i <= a; i++) {
      List<List<List<Score>>> xAxis = new ArrayList<>();
      dp.add(xAxis);

      for (int j = 0; j <= b; j++) {
        // x axis
        List<List<Score>> yAxis = new ArrayList<>();
        xAxis.add(yAxis);

        for (int score : possibleScores) {
          // x player makes score
          if (i - score >= 0) {
            for (List<Score> preScores : dp.get(i - score).get(j)) {
              List<Score> temp = new ArrayList<>(preScores);
              temp.add(new Score(score, 0));
              yAxis.add(temp);
            }
          }

          // y player makes score
          if (j - score >= 0) {
            for (List<Score> preScores : dp.get(i).get(j - score)) {
              List<Score> temp = new ArrayList<>(preScores);
              temp.add(new Score(0, score));
              yAxis.add(temp);
            }
          }
        }
      }
    }

    return dp.get(a).get(b);
  }
}

class Score {
  int x;
  int y;

  public Score(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString(){
    if (x == 0) return "B scores " + y;
    return "A scores " + x;
  }
}

1 combination
B scores 5
A scores 5
A scores 2

2 combination
A scores 5
B scores 5
A scores 2

3 combination
B scores 5
A scores 2
A scores 5

4 combination
A scores 2
B scores 5
A scores 5

5 combination
A scores 5
A scores 2
B scores 5

6 combination
A scores 2
A scores 5
B scores 5

7 combination
A scores 7
B scores 5

8 combination
B scores 5
A scores 7
