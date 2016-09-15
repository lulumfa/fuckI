// O(n*L), space(n*L)

public class ValidWordAbbr {
    HashMap<String, String> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<String, String>();
        for(String s : dictionary){
            String abbr = abbr(s);
            if(map.containsKey(abbr)){
                if(!(map.get(abbr).equals(s))){
                    map.put(abbr, "");
                }
            } else {
                map.put(abbr, s);
            }
        }
    }
    
    public String abbr(String input){
        if(input == null || input.length() <=2) return input;
        return input.charAt(0) + String.valueOf(input.length()-2) + input.charAt(input.length()-1);
    }

    public boolean isUnique(String word) {
        return !map.containsKey(abbr(word)) || map.get(abbr(word)).equals(word);
    }
}
