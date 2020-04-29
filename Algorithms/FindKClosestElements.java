// O(logn + k), space (k) result list
// https://shineboy2013.github.io/2018/01/28/lee-658/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
	int upperIdx = firstEqualOrGreater(arr, x);
	int lowerIdx = upperIdx-1;
	List result = new ArrayList();
	for(int i=k;i>0;i--){
		if(lowerIdx<0)
			result.add(arr[upperIdx++]);
		else if(upperIdx>=arr.length)
			result.add(arr[lowerIdx--]);
		else if(x-arr[lowerIdx]<=arr[upperIdx]-x)
			result.add(arr[lowerIdx--]);
		else
			result.add(arr[upperIdx++]);
		
	}
	Collections.sort(result);
	return result;
}

public int firstEqualOrGreater(int[] a, int key){
	int lo = 0, hi = a.length-1;
	while(lo<=hi){
		int mid =  lo + (hi - lo) / 2;
		if(a[mid]<key)
			lo = mid+1;
		else
			hi = mid-1;
	}
	return lo;
}
}

// magic solution
// https://aaronice.gitbooks.io/lintcode/binary-search/find-k-closest-elements.html

这题最妙的地方在于
搜索k个距离（数组元素之差的绝对值，而不是下标的距离）最近的数中最小的那个下标，这样通过k就可知道右边界的位置
改变binary search中的条件判断语句，改为k个元素中最小值与最大值与x元素的距离绝对值大小的比较 x - arr[mid] vs arr[mid+k] - x
left, right初始值的设定，因为需要[mid + k]，所以right的初始值需要（从arr.length - 1）缩小至 arr.length - k - 1 以防止数组下标越界 
（根据Template #1，#3，如果是Template #2 则需从 arr.length 改为 arr.length - k）

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(arr[left + i]);
        }
        return ans;
    }
}
