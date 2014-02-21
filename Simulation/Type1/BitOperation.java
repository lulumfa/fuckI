import java.util.ArrayList;


public class BitOpration {
	/*Given a number, return the next multiple of 64 that follows it, using bit operations and no loops.
     e.g input = 90, return 128.
     
     The solution is simple. We can get the number by ((int)a/64 + 1) * 64.
     And in bit operation >> 1 means / 2, so >>6 means / 2^6
     
     Contributor: Bo Pang
     */
	
	public static int GetNext64Number(int a)
	{
		return ((a>>6)+1)<<6;
	}
}
