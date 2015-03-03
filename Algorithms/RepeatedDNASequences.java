//http://blog.csdn.net/haiyi727/article/details/43752693

//http://www.cnblogs.com/grandyang/p/4284205.html

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        
        Set<Integer> result=new HashSet<Integer>();
        List<String> result1=new ArrayList<String>();
        Set<Integer> tempList=new HashSet<Integer>();
        int size=s.length();
        String temp;
        if(size<10){
        	return result1;
        }
        else{
        	Map<Character, Integer> map = new HashMap<Character, Integer>();  
            map.put('A', 0);  
            map.put('C', 1);  
            map.put('G', 2);  
            map.put('T', 3); 
            int hash=0;
        	for(int i=0;i<size;i++){
        		if(i<9){
        			hash=(hash<<2)+map.get(s.charAt(i));
        		}
        		else{
        			hash=(hash<<2)+map.get(s.charAt(i));
        			hash&=(1<<20)-1;//整数占32个位，获取其低20位（题中要求是长度为10的子字符串，映射为整数后，子字符串总共占用20位）
        			if(tempList.contains(hash)&&!result.contains(hash)){
            			result1.add(s.substring(i-9,i+1));
            			result.add(hash);
            		}
            		else{
            			tempList.add(hash);
            		}
        		}
        		
        	}
        }
        return result1;
    
    }
}
