//reference: http://blog.csdn.net/linhuanmars/article/details/23972563

public class Solution {
    public String simplifyPath(String path) {
        if(path==null || path.length()==0) return "";
        
        Stack<String> store = new Stack<String>();
        StringBuilder res = new StringBuilder();
        int startIndex;
        for(int i=0; i<path.length(); i++) 
        {
            startIndex = i;
            while(i<path.length() && path.charAt(i)!='/') 
            {
                i++;
            }
            if(startIndex!=i) {
                String temp = path.substring(startIndex, i);
                if(temp.equals("..")) 
                {
                    if(!store.isEmpty())
                    {
                        store.pop();
                    }
                }
                else if(!temp.equals("."))
                {
                    store.push(temp);
                }
            } 
        }
        if(store.isEmpty()) return "/";
        String[] stackList = store.toArray(new String[store.size()]);
        for(int i=0; i<stackList.length; i++ )
        {
            res.append("/" + stackList[i]);
        }
        return res.toString();
    }
}
