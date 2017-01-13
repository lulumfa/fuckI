//reference: http://blog.csdn.net/linhuanmars/article/details/24444491
// O(mn), space O(n)
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0; 
        int x = matrix.length;
        int y = matrix[0].length;
        int max = 0;
        int[] height = new int[y];
        for(int i = 0; i<x; i++) {
            for(int j=0; j<y; j++) {
                height[j] = matrix[i][j] == '1' ? height[j]+1 : 0;
            }
            max = Math.max(max, maxHistogram(height));
        }
        return max;
    }
    
    public int maxHistogram(int[] height) {
        if(height==null || height.length==0) return 0;
        
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;
        
        while(!stack.isEmpty() || i<height.length) {
            if(stack.isEmpty() || (i<height.length && height[i] >=height[stack.peek()])) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                max = Math.max(max, height[index] * (stack.isEmpty() ? i : i - stack.peek() -1));
            }
        }
        return max;
    }
}


// same complexity
//https://discuss.leetcode.com/topic/6650/share-my-dp-solution
class Solution {public:
int maximalRectangle(vector<vector<char> > &matrix) {
    if(matrix.empty()) return 0;
    const int m = matrix.size();
    const int n = matrix[0].size();
    int left[n], right[n], height[n];
    fill_n(left,n,0); fill_n(right,n,n); fill_n(height,n,0);
    int maxA = 0;
    for(int i=0; i<m; i++) {
        int cur_left=0, cur_right=n; 
        for(int j=0; j<n; j++) { // compute height (can do this from either side)
            if(matrix[i][j]=='1') height[j]++; 
            else height[j]=0;
        }
        for(int j=0; j<n; j++) { // compute left (from left to right)
            if(matrix[i][j]=='1') left[j]=max(left[j],cur_left);
            else {left[j]=0; cur_left=j+1;}
        }
        // compute right (from right to left)
        for(int j=n-1; j>=0; j--) {
            if(matrix[i][j]=='1') right[j]=min(right[j],cur_right);
            else {right[j]=n; cur_right=j;}    
        }
        // compute the area of rectangle (can do this from either side)
        for(int j=0; j<n; j++)
            maxA = max(maxA,(right[j]-left[j])*height[j]);
    }
    return maxA;
}
