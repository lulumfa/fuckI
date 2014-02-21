
public class ReverseSentence {

	/**
	 * Reverse words : "I love to play" becomes "play to love I". 
	 * @param word
	 * @param start
	 * @param end
	 */
	
	public static void ReverseWordInSentence(StringBuffer sentence)
	{
		ReverseWord(sentence);
		int wordStartInd = 0;
		for(int i=0; i<sentence.length();i++){
			if(sentence.charAt(i) == ' '){
				ReverseWord(sentence, wordStartInd, i-1);
				wordStartInd = i+1;
			}
		}
	}
	public static void ReverseWord(StringBuffer word)
	{
		int head = 0;
		int tail = word.length()-1;
		char tmp;
		while(head <= tail){
			tmp = word.charAt(head);
			word.setCharAt(head, word.charAt(tail));
			word.setCharAt(tail, tmp);
			head++;
			tail--;
		}
		
	}
	
	
	public static void ReverseWord(StringBuffer word, int start, int end)
	{
		int head = start;
		int tail = end;
		char tmp;
		while(head < tail){
			tmp = word.charAt(head);
			word.setCharAt(head, word.charAt(tail));
			word.setCharAt(tail, tmp);
			head++;
			tail--;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sentence = new StringBuffer("I love to play");
		System.out.println(sentence);
		ReverseWordInSentence(sentence);
		System.out.println(sentence);
		
	}
}
