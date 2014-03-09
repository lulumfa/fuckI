// Queue.java: queue implementation

// Array implementation
// http://www.cs.utsa.edu/~wagner/CS2213/queue/queue.html
public class Queue {
   private int qMaxSize;// max queue size
   private int fp = 0;  // front pointer
   private int rp = 0;  // rear pointer
   private int qs = 0;  // size of queue
   private char[] q;    // actual queue

   public Queue(int size) {
      qMaxSize = size;
      fp = 0;
      rp = 0;
      qs = 0;
      q = new char[qMaxSize];
   }

   public char delete() {
      if (!emptyq()) {
         qs--;
         fp = (fp + 1)%qMaxSize;
         return q[fp];
      }
      else {
         System.err.println("Underflow");
         return '?';
      }
   }

   public void insert(char c) {
      if (!fullq()) {
         qs++;
         rp = (rp + 1)%qMaxSize;
         q[rp] = c;
      }
      else
         System.err.println("Overflow\n");
   }

   public boolean emptyq() {
      return qs == 0;
   }

   public boolean fullq() {
      return qs == qMaxSize;
   }
   
   public void printq() {
      System.out.print("Size: " + qs +
         ", rp: " + rp + ", fp: " + fp + ", q: ");
      for (int i = 0; i < qMaxSize; i++)
         System.out.print("q[" + i + "]=" 
            + q[i] + "; ");
      System.out.println();
   }
}

// linkedList implementation
class Node  
{  
   public int data;  
   public Node next;  
   public Node(int x)  
   {  
     data=x;  
   }  
   public void displayNode()  
   {  
     System.out.print(data+"  ");  
   }  
}  
class LinkList  
{  
   private Node first;  
   private Node last;  
   public LinkList()  
   {  
     first=null;  
     last=null;  
   }  
   public void insertLast(int x)  
   {  
     Node newNode=new Node(x);  
     newNode.next=null;  
     if(isEmpty())  
       first=newNode;  
     else  
       last.next=newNode;  
     last=newNode;  
   }  
   public int deleteFirst()  
   {  
     int t=first.data;  
     if(first.next==null)  
       last=null;  
     first=first.next;  
     return t;  
   }  
   public int peekFirst()  
   {  
     return(first.data);  
   }  
   public boolean isEmpty()  
   {  
     return(first==null);  
   }  
   public void displayList()  
   {  
     Node current=first;  
     while(current!=null)  
     {  
       current.displayNode();  
       current=current.next;  
     }  
   }  
}  
class Queue  
{  
   private LinkList l;  
   public Queue()  
   {  
     l=new LinkList();  
   }  
   public void insert(int x)  
   {  
     l.insertLast(x);  
     System.out.print("Inserted");  
   }  
   public int delete()  
   {  
     return l.deleteFirst();  
   }  
   public boolean isQueueEmpty()  
   {  
     return l.isEmpty();  
   }  
   public void display()  
   {  
     l.displayList();  
   }  
   public int peek()  
   {  
     return l.peekFirst();  
   }  
} 
