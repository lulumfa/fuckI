// in-place constant space heap sort

class Solution {
    public int[] sortArray(int[] A) {
        if (A == null || A.length <= 1) {
            return A;
        }
        buildMaxHeap(A);
        convertHeapToSortedList(A);
        return A;
    }

    private void buildMaxHeap(int[] A) {
        int start = iParent(A.length - 1);
        while (start >= 0) {
            bubbleDown(A, start, A.length - 1);
            start--;
        }
    }

    private void convertHeapToSortedList(int[] A) { // assumes input is a valid heap
        int end = A.length - 1;
        while (end > 0) {
            swap(A, 0, end);
            end--;
            bubbleDown(A, 0, end);
        }
    }

    private void bubbleDown(int[] A, int start, int end) {
        int curr = start;
        while (hasChild(curr, end)) {
            int maxChildIndex = maxChild(A, curr, end);
            if (A[maxChildIndex] > A[curr]) {
                swap(A, curr, maxChildIndex);
                curr = maxChildIndex;
            } else {
                return;
            }
        }
    }

    private int iLeftChild(int i) {
        return 2*i + 1;
    }

    private int iRightChild(int i) {
        return 2*i + 2;
    }

    private int iParent(int i) {
        return (i - 1) / 2;
    }

    private boolean hasChild(int curr, int end) {
        return iLeftChild(curr) <= end;
    }

    private int maxChild(int[] A, int i, int end) { // assumes left child exists
        if (iRightChild(i) > end) {
            return iLeftChild(i);
        } else if (A[iLeftChild(i)] > A[iRightChild(i)]) {
            return iLeftChild(i);
        } else {
            return iRightChild(i);
        }
    }

    private void swap(int[] A, int x, int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
}
