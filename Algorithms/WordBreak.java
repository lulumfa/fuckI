public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
	    ArrayList<Integer> dP = new ArrayList<Integer>();
	    for(int i=s.length()-1;i>=0;i--){
	        if(dict.contains(s.substring(i))){
	            dP.add(i);
	        }else{
	            for(Integer k: dP){
	                if(dict.contains(s.substring(i,k))) {
	                    dP.add(i);
	                    break;
	                }
	            }
	        }
	    }
	    if(dP.size()==0) return false;
	    if(dP.get(dP.size()-1)==0) return true;
	    
        return false;
	}
}
