
// Question:find the intersection of two linked lists

//Explanation: Scan both lists to find the diff of lengths, skip the first several nodes
// in either list in order to make the remaining lists the same length, scan in the same 
// pace and return when same node is found

// The cost is O(n)

//Contributor: Ziyi Jiang
	
public class ListIntersection {
	//let's create a test case
	public static void main(String[] args){
		List common = new List(10);
		common.next = new List(11);//common intersection sub-List is 10>11
		List list1 = new List (1);
		list1.next = new List(2);
		list1.next.next = new List(3);//first list starts with 1>2>3
		list1.next.next.next = common; //this list is 1>2>3>10>11
		
		List list2 = new List(7);
		list2.next = new List(8);
		list2.next.next = common;// so the length is 7>8>10>11
		
		list1.Pirnt();
		list2.Pirnt();
		System.out.println("Intersection of two lists starts from " + Intersectin(list1,list2).value);
	}
	
	// method head accept two lists and return a list
	public static List Intersectin(List a, List b){
		// the first step is to find the diff of lengths of two lists
		int aLength = 0;
		int bLength = 0;
		List cur = a; //cur is the pointer used to keep track of current focus node
		while(cur!=null){
			aLength++;
			cur = cur.next;
		}
		//after the previous loop, aLength records the length of list a
		cur = b; //we copy the code with slight modification to get b length
		while(cur !=null){
			bLength++;
			cur = cur.next;
		}
		//all right we get list a and b's lengths
		//next we do some node skipping if the lengths are not equal
		if(aLength>bLength)// there are more nodes in List a
		{
			for (int i=0; i< aLength-bLength;i++){
				a =a.next;
			 //after this scope of code for cases that lsit a is longer than b, we skipped the necessary nodes to make the remaining a and b equal length
			}
		}
		else if(bLength>aLength){// do the same for list b if length b larger than a
			
			for (int i=0; i< bLength-aLength;i++){
				b =b.next;
			
			}	
		}
		//now we are pretty sure the remaining a and b are equal length
		while(a!=b && a!=null && b!=null){
			a = a.next;
			b = b.next; //we keep the same pace by scanning both lists to its next together
		
		// after that loop , a =b and it is the value we are searching for!
		}
		return a;// the point is where a=b and that's the intersection point!
	}
		
	
}
//create the list definition as below
	

	class List{
		List next;
		int value;
		public List(int k){
			value = k;
		}
		public void Pirnt(){
			List current = this;
			while(current!=null){
				System.out.print(current.value + "->");
				current = current.next;
			}
			System.out.print("NULL\n");
		}
	}
