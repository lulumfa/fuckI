// O(max(m, n)), space (max(m, n))
class Solution {
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  int len1 = findLength(l1);
      int len2 = findLength(l2);    

    /*If the lists are not same size, padd the smaller one with zeros on the left.*/
      if(len1 < len2){
          l1 = paddWithZeros(l1, len2 - len1);
      } else if(len2 < len1){
          l2 = paddWithZeros(l2, len1 - len2);
      }
      /* will begin with 0 in the list. Treat this 0 as you carry */
      ListNode result = new ListNode(0);

      result = addTwoNumbersUtil(l1, l2, result);   

    /*if the carry is 0 for the first digits in the lists, result.val will be zero. So omit that 0 node*/
      if(result.val == 0){
          return result.next;
      }        

      return result;


  }

  /* Creates a Sub Sum node and Carry node on the last recursive call. Result always points to the carry node. On the way down the call stack, it modifies the carry node with the current sub sum value*/
  public ListNode addTwoNumbersUtil(ListNode l1, ListNode l2, ListNode result){

      if(l1 == null && l2 == null){
          return result;

      } else {
          result = addTwoNumbersUtil(l1.next, l2.next, result);

          int sum = l1.val + l2.val + result.val;           
          int carry = sum/10;
          sum = sum%10;
          result.val = sum;
          ListNode carryNode = new ListNode(carry);
          carryNode.next = result;            
          result = carryNode;           

          return result;

      }


  }

  public int findLength(ListNode node){

      int len = 0;
      while(node != null){
          len++;
          node = node.next;
      }

      return len;
  }


  public ListNode paddWithZeros(ListNode node, int lenDiff){

      while(lenDiff > 0){

          ListNode newNode = new ListNode(0);
          newNode.next = node;
          node = newNode;

          lenDiff --;
      }
      return node;
  }
}
