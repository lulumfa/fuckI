// np issue

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    List<Double> prices = new ArrayList<Double>() {{
      add(10.02);
      add(1.11);
      add(2.22);
      add(3.01); 
      add(4.02); 
      add(2.00);
      add(5.03);
    }};
    double target = 7.03;
    System.out.println(String.valueOf(
      Solution.findCombinations(prices, target)));  
  }
    
  public static List<List<Double>> findCombinations(List<Double> prices, double target) {
    List<List<Double>> res = new ArrayList<List<Double>>();
    if (prices == null || prices.size() == 0) return res;
    int targetCent = (int) Math.round(target);
    Collections.sort(prices);
    List<Integer> pricesCent = new ArrayList<Integer>(prices.size());
    for (Double price : prices) {
      pricesCent.add((int) Math.round(price));
    }
    dfsFindCombinations(res, prices, pricesCent, targetCent, 0, new ArrayList<Double>());
    return res;
  }
  
  private static void dfsFindCombinations(List<List<Double>> res, List<Double> prices, List<Integer> pricesCent, int targetCent, int start, List<Double> list) {
    if (targetCent == 0) {
      res.add(new ArrayList<Double>(list));
      return;
    }
    
    for (int i = start; i < pricesCent.size(); i++) {
      if (i > start && pricesCent.get(i) == pricesCent.get(i-1)) {
        continue;
      }
      list.add(prices.get(i));
      dfsFindCombinations(
        res, prices, pricesCent, targetCent - pricesCent.get(i), i + 1, list);
      list.remove(list.size() -1);
    }
  }
}
