public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pt = new ArrayList<List<Integer>>();
        if(numRows==0) return pt;
        List<Integer> lastRow;
        for(int i= 0; i<numRows; i++) {
            List<Integer> row = new ArrayList<Integer>(i+1);
            row.add(1);
            for(int j =1; j< i; j++) {
                row.add(pt.get(i-1).get(j-1) + pt.get(i-1).get(j));        
            }
            if(i!=0) row.add(1);

            pt.add(row);
            
        }
        return pt;
    } 
}
