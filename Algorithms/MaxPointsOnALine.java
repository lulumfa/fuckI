/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) 
    {
        if(points ==null) return 0;
        int len = points.length;
        if(len<=2) return len;
        int res = 2;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int[] convert;
        int count = 0;
        for(int i =0; i< len; i++)
        {
            int dup = 1;
            temp.clear();
            for(int j = 0; j< len; j++)
            {
                if(i==j) continue;
              
                if(points[i].x==points[j].x)
                {
                    if(points[i].y==points[j].y)
                    {
                        dup++;
                    }
                    else
                    {
                        temp.add(9999);
                    }
                }
                else
                {
                    temp.add(10000*(points[i].y-points[j].y)/(points[i].x-points[j].x));
                }
            }
            if(dup==len) return len;
            if(temp.size()==0) continue;
            convert = new int[temp.size()];
            for(int k=0; k < temp.size(); k++)
            {
                convert[k] = temp.get(k);
            }
            Arrays.sort(convert);
            count = 1;
            for(int k=1; k< convert.length;k++)
            {
                if(convert[k-1]==convert[k])
                {
                    count++; 
                }
                else
                {
                    if((count+dup)>res)
                    {
                        res = count+dup;
                    }
                    count=1;
                }
            }
            if((count+dup)>res)
            {
                res = count+dup;
            }
        }
        return res;
    }
}
