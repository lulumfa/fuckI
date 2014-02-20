public class Solution {
    public int sqrt(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(x<0) return -1;
        if(x==0) return 0;
        
        double y = ((double)x)/2.;
        while(Math.abs(y*y-x)>0.00001){
            y=(y+x/y)/2.;
        }
        return (int) y;
    }
}
