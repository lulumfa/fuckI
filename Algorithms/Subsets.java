// http://www.programcreek.com/2013/01/leetcode-subsets-java/
// Given a set S of n distinct integers, there is a relation between Sn and Sn-1. The subset of Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}. Therefore, a Java solution can be quickly formalized.

public ArrayList<ArrayList<Integer>> subsets(int[] S) {
	if (S == null)
		return null;
 
	Arrays.sort(S);
 
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
 
	for (int i = 0; i < S.length; i++) {
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
 
		//get sets that are already in result
		for (ArrayList<Integer> a : result) {
			temp.add(new ArrayList<Integer>(a));
		}
 
		//add S[i] to existing sets
		for (ArrayList<Integer> a : temp) {
			a.add(S[i]);
		}
 
		//add S[i] only as a set
		ArrayList<Integer> single = new ArrayList<Integer>();
		single.add(S[i]);
		temp.add(single);
 
		result.addAll(temp);
	}
 
	//add empty set
	result.add(new ArrayList<Integer>());
 
	return result;
}
