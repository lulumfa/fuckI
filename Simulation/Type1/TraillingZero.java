/**
 * Calculate the trailling zero in n!
 * @param n
 * @return
 * Contributor: Bo Pang
 
 */
public static int traillingZero(int n){
int count =0;
if(n<0){
System.out.println("Factorial is not defined for n < 0");
return 0;
}
for(int i=5; n/i > 0; i*=5){
count += n/i;
}

return count;
}