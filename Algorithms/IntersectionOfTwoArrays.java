// O(m + n), space (m + n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Set<Integer> set1 = new HashSet<Integer>(); 
        for (Integer num : nums1) set1.add(num);
        Set<Integer> set2 = new HashSet<Integer>(); 
        for (Integer num : nums2) set2.add(num);
        
        if (set1.size() > set2.size()) {
            Set<Integer> temp = set2;
            set2 = set1;
            set1 = temp;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer num : set1) {
            if (set2.contains(num)) list.add(num);
        }
        
        int[] res = new int[list.size()];
        int index = 0;
        for (Integer num : list) res[index++] = num;
        return res;
    }
}
