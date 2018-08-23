// O(nk^2) = O(n), space O(n) i think.

// ask upper case, lower case

// KMP way, have not tried(same complexity) https://discuss.leetcode.com/topic/43848/code-redo-40-lines-o-n-k-solution-using-kmp

class Solution {
    class Node{
            Node[] next;
            int index;
            List<Integer> list;
            Node(){
                next=new Node[26];
                index=-1;
                list=new ArrayList<>();
            }
        }
        
    public List<List<Integer>> palindromePairs(String[] words) {
        Node root=new Node();
        List<List<Integer>> res=new ArrayList();
        for(int i=0;i<words.length;i++) addnode(words[i],i,root);
        for(int i=0;i<words.length;i++) searchnode(words[i],i,root,res);
        return res;
    }
    private void addnode(String word,int idx,Node root){  
        for(int i=word.length()-1;i>=0;i--){
            if(isP(word,0,i)) root.list.add(idx);
            char c=word.charAt(i);
            if(root.next[c-'a']==null) root.next[c-'a']=new Node();
            root=root.next[c-'a'];
        }
        root.index=idx;
        root.list.add(idx);
    }
    private void searchnode(String word,int idx,Node root,List<List<Integer>> res){
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(root.index!=-1 && root.index!=idx && isP(word,i,word.length()-1)) res.add(Arrays.asList(idx,root.index));
            root=root.next[c-'a'];
            if(root==null) return;
        }
        for(int j:root.list){
            if(j==idx) continue;
            res.add(Arrays.asList(idx,j));
        }
        
        
    }
     private boolean isP(String word, int i,int j){
        while(i<j){
            if(word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }
   
    
}



public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(words == null || words.length < 2) return res;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i< words.length; map.put(words[i], i), i++);
        
        if(map.containsKey("")){
            int index = map.get("");
            for(int i = 0; i< words.length; i++){
                if(index != i && isPalindrome(words[i])) {
                    res.add(Arrays.asList(index, i));
                    res.add(Arrays.asList(i, index));
                }
            }
        }
        
        for(String word : words) {
            String rev = strrev(word);
            if(!rev.equals(word) && map.containsKey(rev)) {
                res.add(Arrays.asList(map.get(word), map.get(rev)));
            }
        }
        
        for(String word : words) {
            for(int i = 1; i< word.length(); i++) {
                if(isPalindrome(word.substring(0, i))) {
                    String rev = strrev(word.substring(i, word.length()));
                    if(map.containsKey(rev)){
                        res.add(Arrays.asList(map.get(rev), map.get(word)));
                    }
                }
            }
        }
        
        for(String word : words) {
            for(int i = word.length()-1; i>0 ;i--) {
                if(isPalindrome(word.substring(i, word.length()))) {
                    String rev = strrev(word.substring(0, i));
                    if(map.containsKey(rev)){
                        res.add(Arrays.asList(map.get(word), map.get(rev)));
                    }
                }
            }
        }
        return res;
    }
    
    private String strrev(String input){
        if(input == null) return input;
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
    
    private boolean isPalindrome(String input) {
        if(input == null) return false;
        int left = 0;
        int right = input.length()-1;
        while(left <= right){
            if(input.charAt(left++) != input.charAt(right--)) return false;
        }
        return true;
    }
}
