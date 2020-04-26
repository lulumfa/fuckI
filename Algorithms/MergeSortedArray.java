// reference: http://blog.csdn.net/linhuanmars/article/details/19712333

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        if(A==null || B==null) return;
        int i = m+n-1;
        int j = m-1;
        int k = n-1;
        while( j>=0 && k >=0) {
            if(A[j]<B[k]) {
                A[i--] = B[k--];
            } else {
                A[i--] = A[j--];
            }
        }
        while(k>=0) {
            A[i--] = B[k--];
        }
    }
}

// using iterator
public static List<Integer> merge(List<Integer> listA, List<Integer> listB)
    {
        if (listA == null) return listB;
        if (listB == null) return listA;

        final ListIterator<Integer> iterA = listA.listIterator(0);
        final ListIterator<Integer> iterB = listB.listIterator(0);

        final List<Integer> result = new LinkedList<Integer>();
    
        merge(iterA, iterB, result);
        return result;
    }

    private static void merge(ListIterator<Integer> iterA,
                              ListIterator<Integer> iterB,
                              List<Integer>  result)
    {
        if (!iterA.hasNext())
        {
            while (iterB.hasNext()) { result.add(iterB.next()); }
            return;
        }

        if (!iterB.hasNext())
        {
            while (iterA.hasNext()) {result.add(iterA.next()); }
            return;
        }

        Integer a = iterA.next();
        Integer b = iterB.next();

        if (a < b)
        {
            result.add(a);
            b = iterB.previous(); // rewind
        }
        else if (b < a)
        {
            result.add(b);
            a = iterA.previous(); // rewind
        }
        else
        {
            result.add(a);
            result.add(b);
        }

        merge(iterA, iterB, result);
    }
