import java.util.ArrayList;

//Problem 
//Give a string, return all the Permutation.
//e.g. Given "abc", return "abc", "acb", "bac", "bca", "cab", "cba"
//Order does not matter

//Solve recursively

//Contributor: Bo Pang


public class Permutation {
	private int num;
	private ArrayList<String> allPermutation;
	
	public Permutation(ArrayList<String> input){
		this.num = input.size();
		this.allPermutation = input;
		
	}
	
	public static Permutation getPermutationOfString(String input){
		ArrayList<String> permutations = new ArrayList<String>();
		generatePermutations(input.toCharArray(), 0, permutations);
		return new Permutation(permutations);
	}
	
	public static void generatePermutations(char[] input, int index, ArrayList<String> permutations){
		if(index == input.length){
			permutations.add(String.copyValueOf(input));
			System.out.println(String.copyValueOf(input));
			return;
		}
		for(int i = index; i<input.length; i++){
			char[] newInput = input.clone();
			char tmp;
			tmp = newInput[index];
			newInput[index] = newInput[i];
			newInput[i] = tmp;
			generatePermutations(newInput, index+1,permutations);
		}
	}
	
	public int getNum(){
		return num;
	}
	
	public ArrayList<String> getAllPermutation(){
		return allPermutation;
	}
}
