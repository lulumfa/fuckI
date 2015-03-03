// http://blog.csdn.net/linhuanmars/article/details/20884585
//这样一来我们只需要扫描数组两遍，时间复杂度是O(2*n)=O(n)，而且利用数组本身空间，
//只需要一个额外变量，所以空间复杂度是O(1)。

public int firstMissingPositive(int[] A) {
    if(A==null || A.length==0)
    {
        return 1;
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i])
        {
            int temp = A[A[i]-1];
            A[A[i]-1] = A[i];
            A[i] = temp;
            i--;
        }
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]!=i+1)
            return i+1;
    }
    return A.length+1;
}
