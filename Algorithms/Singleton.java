package Leet;


//http://www.javaworld.com/article/2073352/core-java/simply-singleton.html


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
   // setu up the threads
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
// output

Buildfile: build.xml
init:
     [echo] Build 20030414 (14-04-2003 03:06)
compile:
run-test-text:
INFO Thread-1: sleeping...
INFO Thread-2: created singleton: Singleton@7e5cbd
INFO Thread-1: created singleton: Singleton@704ebb
junit.framework.AssertionFailedError: expected:<true> but was:<false>
   at junit.framework.Assert.fail(Assert.java:47)
   at junit.framework.Assert.failNotEquals(Assert.java:282)
   at junit.framework.Assert.assertEquals(Assert.java:64)
   at junit.framework.Assert.assertEquals(Assert.java:149)
   at junit.framework.Assert.assertEquals(Assert.java:155)
   at SingletonTest$SingletonTestRunnable.run(Unknown Source)
   at java.lang.Thread.run(Thread.java:554)
     [java] .
     [java] Time: 0.577
     [java] OK (1 test)
// Making Example 4's singleton class thread-safe is easy—just synchronize the getInstance() method like this:

public synchronized static Singleton getInstance() {
   if(singleton == null) {
      simulateRandomActivity();
      singleton = new Singleton();
   }
   logger.info("created singleton: " + singleton);
   return singleton;
}

// after modification

Buildfile: build.xml
init:
     [echo] Build 20030414 (14-04-2003 03:15)
compile:
    [javac] Compiling 2 source files
run-test-text:
INFO Thread-1: sleeping...
INFO Thread-1: created singleton: Singleton@ef577d
INFO Thread-2: created singleton: Singleton@ef577d
     [java] .
     [java] Time: 0.513
     [java] OK (1 test)
//This time, the test case works and our multithreading worries are over; however,
// the astute reader may realize that the getInstance() method only needs to be synchronized the first time it is called.
// Because synchronization is very expensive performance-wise (synchronized methods can run up to 100 times slower than
// unsynchronized methods), perhaps we can introduce a performance enhancement that only synchronizes the singleton 
// assignment in getInstance().
    
// simple synchronization and double-checking are not working as they seem    
     
// An alternative thread-safe singleton implementation

// Example 7 lists a simple, fast, and thread-safe singleton implementation:

// Example 7. A simple singleton

public class Singleton {
   public final static Singleton INSTANCE = new Singleton();
   private Singleton() {
         // Exists only to defeat instantiation.
      }
}
Singleton singleton = Singleton.INSTANCE;
      singleton.dothis();
      singleton.dothat();
      ...
// You can safely use Example 7's singleton implementation or Example 1's implementation with a synchronized 
// getInstance() method. However, we must explore another issue: You must specify the singleton class at compile time,
// which is not very flexible. A registry of singletons will let us specify singleton classes at runtime.

//Use a singleton registry to:

// 1. Specify singleton classes at runtime
// 2. Prevent singleton subclasses from allowing multiple instances

// The preceding base class creates subclass instances and stores them in a map. 
// But that base class is high maintenance because you must update its getInstance() method for every subclass. 
// Luckily, we can use reflection to skirt that issue.


// One more thing concerning singleton registries: they should be encapsulated in their own class for maximum reuse.

import java.util.HashMap;
import org.apache.log4j.Logger;
public class SingletonRegistry {
   public static SingletonRegistry REGISTRY = new SingletonRegistry();
   private static HashMap map = new HashMap();
   private static Logger logger = Logger.getRootLogger();
   protected SingletonRegistry() {
      // Exists to defeat instantiation
   }
   public static synchronized Object getInstance(String classname) {
      Object singleton = map.get(classname);
      if(singleton != null) {
         return singleton;
      }
      try {
         singleton = Class.forName(classname).newInstance();
         logger.info("created singleton: " + singleton);
      }
      catch(ClassNotFoundException cnf) {
         logger.fatal("Couldn't find class " + classname);    
      }
      catch(InstantiationException ie) {
         logger.fatal("Couldn't instantiate an object of type " + 
                       classname);    
      }
      catch(IllegalAccessException ia) {
         logger.fatal("Couldn't access class " + classname);    
      }
      map.put(classname, singleton);
      return singleton;
   }
}
// Notice I implemented the SingletonRegistry class as a singleton. I also generalized the registry so it can store 
// and retrieve any type of object. Example 11 shows a Singleton class that uses the registry:
import java.util.HashMap;
import org.apache.log4j.Logger;
public class Singleton {
   protected Singleton() {
      // Exists only to thwart instantiation.
   }
   public static Singleton getInstance() {
      return (Singleton)SingletonRegistry.REGISTRY.getInstance(classname);
   }
}
// ow that we've seen how to implement thread-safe singletons and how to use a registry to specify singleton class names at runtime, let's examine how to deal with classloaders and serialization.


