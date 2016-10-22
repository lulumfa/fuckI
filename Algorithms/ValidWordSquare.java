O(mn) in place

public class Solution {
    public boolean validWordSquare(List<String> words) {
        if(words == null || words.size() == 0) return false;
        
        for(int i = 0; i < words.size(); i++) {
            for(int j = 0; j < words.get(i).length(); j++) {
                if(j>= words.size() || i >= words.get(j).length() || words.get(j).charAt(i) != words.get(i).charAt(j)) return false;
            }
        }
        return true;
    }
}
