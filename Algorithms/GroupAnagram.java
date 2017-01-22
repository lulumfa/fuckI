// O(n*klgk), sorting n = a * b, O(a * blgb) = O(nlgb) close to linear

//http://www.programcreek.com/2014/04/leetcode-anagrams-java/

public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<List<String>>();
 
    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    for(String str: strs){
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        String ns = new String(arr);
 
        if(map.containsKey(ns)){
            map.get(ns).add(str);
        }else{
            ArrayList<String> al = new ArrayList<String>();
            al.add(str);
            map.put(ns, al);
        }
    }
 
    for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()){
        Collections.sort(entry.getValue());
    }
 
    result.addAll(map.values());
 
    return result;
}
