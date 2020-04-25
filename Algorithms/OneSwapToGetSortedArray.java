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
