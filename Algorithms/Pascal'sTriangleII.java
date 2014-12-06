// reference: http://blog.csdn.net/linhuanmars/article/details/23311629
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        if(rowIndex<0) return row;
        row.add(1);
        for(int i = 1; i<= rowIndex; i++) {
            for(int j = i-2; j >=0; j--) {
                row.set(j+1, row.get(j+1) + row.get(j));
            }
            row.add(1);
        }
        return row;
    }
}
