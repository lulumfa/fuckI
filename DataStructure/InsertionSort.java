/*Simple implementation
Efficient for (quite) small data sets
Adaptive (i.e., efficient) for data sets that are already substantially sorted: the time complexity is O(n + d), where d is the number of inversions
More efficient in practice than most other simple quadratic (i.e., O(n2)) algorithms such as selection sort or bubble sort; the best case (nearly sorted input) is O(n)
Stable; i.e., does not change the relative order of elements with equal keys
In-place; i.e., only requires a constant amount O(1) of additional memory space
Online; i.e., can sort a list as it receives it
*/

// http://en.wikipedia.org/wiki/Insertion_sort
public class InsertionSort{
  public static void main(String a[]){
    int i;
    int array[] = {12,9,4,99,120,1,3,10};
    System.out.println("\n\n RoseIndia\n\n");
    System.out.println(" Selection Sort\n\n"); 
    System.out.println("Values Before the sort:\n");  
    for(i = 0; i < array.length; i++)
    System.out.print( array[i]+"  ");
    System.out.println();
    insertion_srt(array, array.length);  
    System.out.print("Values after the sort:\n");  
    for(i = 0; i <array.length; i++)
    System.out.print(array[i]+"  ");
    System.out.println(); 
    System.out.println("PAUSE"); 
  }

  public static void insertion_srt(int array[], int n){
    for (int i = 1; i < n; i++){
    int j = i;
    int B = array[i];
      while ((j > 0) && (array[j-1] > B)){
        array[j] = array[j-1];
        j--;
      }
      array[j] = B;
    }
  }
}
