package Leet;

public class Singleton {
	private static Singleton instance = null;
	protected Singleton(){}
	
	public static Singleton getInstance()
	{
		if(instance==null) instance = new Singleton();
		return instance;
	}
	
	public static void main(String args[])
	{
		Singleton case1 = null, case2 = null;
		case1 = Singleton.getInstance();
		case2 = Singleton.getInstance();
		if(case1==case2)
		{
			System.out.println(true);
		}
		System.out.println(case1);
		System.out.println(case2);
	}
}


// multi-thread issue

// simulate the multi-thread problem by pausing one thread to wait for the second one
import org.apache.log4j.Logger;
public class Singleton {
  private static Singleton singleton = null;
  private static Logger logger = Logger.getRootLogger();
  private static boolean firstThread = true;
  protected Singleton() {
    // Exists only to defeat instantiation.
  }
  public static Singleton getInstance() {
     if(singleton == null) {
        simulateRandomActivity();
        singleton = new Singleton();
     }
     logger.info("created singleton: " + singleton);
     return singleton;
  }
  private static void simulateRandomActivity() {
     try {
        if(firstThread) {
           firstThread = false;
           logger.info("sleeping...");
           // This nap should give the second thread enough time
           // to get by the first thread.
             Thread.currentThread().sleep(50);
       }
     }
     catch(InterruptedException ex) {
        logger.warn("Sleep interrupted");
     }
  }
}

// test for this
import org.apache.log4j.Logger;
import junit.framework.Assert;
import junit.framework.TestCase;
public class SingletonTest extends TestCase {
   private static Logger logger = Logger.getRootLogger();
   private static Singleton singleton = null;
   public SingletonTest(String name) {
      super(name);
   }
   public void setUp() {
      singleton = null;
   }
   public void testUnique() throws InterruptedException {
      // Both threads call Singleton.getInstance().
      Thread threadOne = new Thread(new SingletonTestRunnable()),
             threadTwo = new Thread(new SingletonTestRunnable());
      threadOne.start(); //Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
      threadTwo.start();
      threadOne.join(); //Waits for this thread to die.
      threadTwo.join();
   }
   private static class SingletonTestRunnable implements Runnable {
      public void run() {
         // Get a reference to the singleton.
         Singleton s = Singleton.getInstance();
         // Protect singleton member variable from
         // multithreaded access.
         synchronized(SingletonTest.class) {
            if(singleton == null) // If local reference is null...
               singleton = s;     // ...set it to the singleton
         }
         // Local reference must be equal to the one and
         // only instance of Singleton; otherwise, we have two
                  // Singleton instances.
         Assert.assertEquals(true, s == singleton);
      }
   }
}
