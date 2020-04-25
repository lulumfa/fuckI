// O(n) space constant
// https://www.geeksforgeeks.org/check-if-array-can-be-sorted-with-one-swap/
static boolean checkSorted(int n, int arr[]) 
{ 
    // Find counts and positions of  
    // elements that are out of order. 
    int first = 0, second = 0; 
    int count = 0; 
    for (int i = 1; i < n; i++)  
    { 
        if (arr[i] < arr[i - 1]) 
        { 
            count++; 
            if (first == 0) 
                first = i; 
            else
                second = i; 
        } 
    } 
  
    // If there are more than two elements 
    // are out of order. 
    if (count > 2) 
        return false; 
  
    // If all elements are sorted already 
    if (count == 0) 
        return true; 
  
    // Cases like {1, 5, 3, 4, 2} 
    // We swap 5 and 2. 
    if (count == 2) 
        swap(arr, first - 1, second); 
  
    // Cases like {1, 2, 4, 3, 5} 
    else if (count == 1) 
        swap(arr, first - 1, first); 
  
    // Now check if array becomes sorted 
    // for cases like {4, 1, 2, 3} 
    for (int i = 1; i < n; i++) 
        if (arr[i] < arr[i - 1]) 
            return false; 
  
    return true; 
} 
  
static int[] swap(int []arr, int i, int j) 
{ 
    int temp = arr[i]; 
    arr[i] = arr[j]; 
    arr[j] = temp; 
    return arr; 
} 


https://www.geeksforgeeks.org/sort-an-almost-sorted-array-where-only-two-elements-are-swapped/
// O(n) , space (1)
static void sortByOneSwap(int arr[],  
                          int n) 
{ 
    // Traverse the given array 
    // from rightmost side 
    for (int i = n - 1; i > 0; i--) 
    { 
        // Check if arr[i]  
        // is not in order 
        if (arr[i] < arr[i - 1]) 
        { 
            // Find the other element  
            // to be swapped with arr[i] 
            int j = i - 1; 
            while (j >= 0 && arr[i] < arr[j]) 
                j--; 
  
            // Swap the pair 
            int temp = arr[i]; 
            arr[i] = arr[j + 1]; 
            arr[j + 1] = temp; 
      
            break; 
        } 
    } 
} 
