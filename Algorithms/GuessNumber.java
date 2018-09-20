// at most 17 times
// there might be another way which is using fewer api calls, in the pdf

package zillow;

public class GuessNumber {
	
	public static void main(String[] args) {
		GuessNumber gn = new GuessNumber("6666", 6);
		System.out.println(gn.guessNumber());
	}

private String secret;
private int n;
  public GuessNumber(String secret, int n) {
	  this.secret = secret;
	  this.n = n;
  } 
  
  private String guessNumber() {
	  char[] candidate = new char[] {'1', '1', '1', '1'};
	  
	  StringBuilder res = new StringBuilder();
	  int count = 0;
	  int initialGuess = getHint(String.valueOf(candidate));
	  count++;
	  for (int i = 0; i <4; i++) {
		  for (int j = 2; j < n; j++) {
			  candidate[i] = (char)('0' + j);
			  int guess = getHint(String.valueOf(candidate));
			  count++;
			  if (guess < initialGuess) {
				  res.append('1');
				  break;
			  } else if (guess > initialGuess) {
				  res.append((char)('0' + j));
				  break;
			  } else if (j == n -1) {
				  res.append((char)('0' + j + 1));
				  break;
			  }
		  }
		  candidate[i] = '1';
	  }
	  return res.append(count).toString();
  }
	
  public int getHint(String guess) {
        int[] sdigits = new int[10];
        int[] gdigits = new int[10];
        int[] mdigits = new int[10];
        int As = 0;
        int Bs = 0;
        int len = secret.length();
        for(int i = 0; i < len; i++) {
            char sc = secret.charAt(i);
            sdigits[sc - '1']++;
	        char gc = guess.charAt(i);
	        gdigits[gc - '1']++;
	        if(sc == gc) {
	            mdigits[sc - '1']++;
	        }
	    }
	    for(int i = 0; i < 10; i++) {
	        As += mdigits[i];
	        Bs += Math.max(1, Math.min(sdigits[i], gdigits[i]) - mdigits[i]);
	    }
	    return As;
  }
}
