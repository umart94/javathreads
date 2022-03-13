package synchronization;


public class Main {

    public static void main(String[] args) {
    	/*
    	 Interference happens when two operations, running in different threads, but acting on the same data, interleave.
    	  This means that the two operations consist of multiple steps, and the sequences of steps overlap
    	 here if we only have a single countdown object instance for each thread interference will happen
    	 */
        
    	Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
        
    	//the solution is to have seperate countdown object for each thread
    	/*
    	 but in this solution suppose we originally had bankaccount objects
    	 this solution would fail, because we need original instance of the object
    	 threads making payments etc.
    	 
    	 integrity of bank balance value
    	 prevent race condition
    	 */
    	
    	
    	
    	
    	
    	/*
    	 process of controlling when threads execute code
    	 and can access the heap 
    	 is called synchronization
    	 
    	 only 1 thread can execute that method at a time
    	 the other threads would get suspended while accessing the
    	 shared object.
    	 
    	 
    	 since only 1 thread can execute a synchronized method
    	 threads cannot interleave and cannot cause interference.
    	 
    	 prevents thread interference within synchronized methods
    	 (but not outside methods)
    	 
    	 we would have to synchronize all areas then
    	 */
    	
       	//we cannot use syncrhonization in constructors
    	//because the object is accessed by 1 thread while instantiating
    	//and also that object wont be available for use until that thread has finished instantiating it
    	
    	
    	
    	
    	
    }
}

class Countdown {

    private int i;

    public synchronized void doCountdown() {
        
    	
    	//we can also synchronize a block of statements rather than entire methods
    	//every java object has an intrinsic lock or monitor lock.
    	
    	/*
    	 we can synchronize a block of statements that work with an object
    	 by forcing threads to acquire object's intrinsic lock or monitor lock
    	 before they execute the statement or block
    	 
    	 
    	 only 1 thread can hold the lock at a time
    	 so other threads that want the lock would be suspended
    	 until running thread releases the lock.
    	 
    	 
    	 then only 1 of the waiting threads can get that lock
    	 and continue executing the block of statements.
    	 
    	 */
    	
       /* for(i=10; i > 0; i--) {
            System.out.println(Thread.currentThread().getName() + ": i =" + i);
        }*/
        
        //primitive types do not have instrinsic locks
        //intrinsic belonging naturally
        
        synchronized(this) {
        	for(i=10; i > 0; i--) {
                System.out.println(Thread.currentThread().getName() + ": i =" + i);
            }
        }
        
        /*
         we can also synchronize static objects and static methods
         lock is owned by class object associated by objects class
         */
        
        /*
         synchronization is reentrant
         what it means is that if a thread acquires an objects lock
         and within synchronize block it calls a method
         thats using the same object to synchronize some code
         
         the thread can keep executing because it already has
         the objects lock
         
         a thread can acquire a lock it already owns
         
         */
        
        /*
         critical section refers to the section of code
         accessing a shared resource or variable.
         
         thread safe means that developer has synchronized 
         all critical sections of a code.
         */
        
    
}
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
 
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}