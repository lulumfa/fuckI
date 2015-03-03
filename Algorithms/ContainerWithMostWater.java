//http://blog.csdn.net/linhuanmars/article/details/21145429

public int maxArea(int[] height) {
    if(height==null || height.length==0)
        return 0;
    int l = 0;
    int r = height.length-1;
    int maxArea = 0;
    while(l<r)
    {
        maxArea = Math.max(maxArea, Math.min(height[l],height[r])*(r-l));
        if(height[l]<height[r])
            l++;
        else
            r--;
    }
    return maxArea;
}
