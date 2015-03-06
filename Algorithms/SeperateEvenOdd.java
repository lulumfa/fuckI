//http://www.geeksforgeeks.org/segregate-even-and-odd-numbers/

private void separateEvenOdd(int[] list) {

if(list==null || list.length==0) return;

int left = 0;

int right = list.length-1;

while(left<right) {

while(list[left]%2==0) {

left++;

if(left==right) return;

}

while(list[right]%2==1) {

right--;

if(right==left) return;

}

int temp = list[right];

list[right] = list[left];

list[left] = temp;

}

}
