// http://blog.csdn.net/linhuanmars/article/details/24761459

// 所以总时间复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)。

public class Solution {
    public int numTrees(int n) {
        if(n<1) return 0;
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i<=n; i++) {
            for(int j = 0; j<i; j++) {
                res[i] += res[j] * res[i-j-1];
            }
        }
        return res[n];
    }
}
// Note 1. 第一种方法，用的DP。 第二种方法，考虑f(n) 和 f(n-1) 的关系， 很直接。
// Link : 阿三哥有个视频链接，讲这题，讲的很清楚，http://www.youtube.com/watch?v=UfA_v0VmiDg
//           不得不说，阿三哥做视频有一套，经常看youtube的视频都是三哥做的，讲的很清楚，印度英语差点就是了。

public int numTrees(int n) {
                        if(n == 0) return 1;
                        if(n == 1) return 1;
                        
                        int[] f = new int[n+1];
                        f[0] = 1;
                        f[1] = 1;
                        for(int i = 2; i < n+1; i++) {
                             for(int j = 1 ; j <= i; j++) {
                                    f[i] += f[i-j] * f[j-1];
                             }
                        }
                        return f[n];
                 } 
                 
                  public int numTrees(int n) { 
                     int c = 1; 
                     for (int i = 2; i <= n; i++) 
                            c = 2*(2*i-1)*c/(i+1);
                     return c;
                }
