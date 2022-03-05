package demo;

public class Main {

	public static void main(String[] args) {
		System.out.println("1) ------------- MAIN THREAD ------");
		
		/*1. instance of thread class
		2. tell thread what code we want to run
		subclass of thread class overriding run method.
		*/
		Thread secondThread = new AnotherThread();
		secondThread.start();//enables jvm to enable run method for the thread.
		
		System.out.println("1) ------------- MAIN THREAD ------");
		
		
		
		
		/*
		 * if we use a named class that is subclass of thread
		 * we are not allowed to start the same instance of the thread more than once
		 * 
		 * we would need to create a new instance inside main method.
		 * so what we can do is rather than creating a named class use anonymous classes
		 */
		//secondThread.start();//throws java.lang.IllegalThreadStateException
		
		
		
		
		/*
		 * we can create a thread using anonymous class
		 * this is done so that the thread code can only be run once
		 * we have to start the thread immediately
		 */
		
		//THIRD THREAD
		new Thread() {

			
			public void run() {
				System.out.println("3) ------------- THIRD THREAD ----- ANONYMOUS CLASS THREAD ------");
				
			}
			
		}.start();
		
		System.out.println("1) ------------- MAIN THREAD ------");
		
		
		
		
		
		
		
		
		//fourth thread
		//have a class implement Runnable interface 
		//inside main create instance of thread class
		//pass instance of runnable class to thread class constructor
		
		
		Thread fourthThread = new Thread(new FourthThreadRunnable());
		fourthThread.start();
		
		//in start method , multithreading takes place
		//in run method, the code is run on same thread where you called run method.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//fifth thread
		//create an anonymous class, it implements runnable and pass runnable constructor to anonymous class constructor.
		
		Thread fifthThreadRunnable = new Thread(new FourthThreadRunnable() {
			@Override
			public void run() {
				System.out.println("5.) FIFTH THREAD ANONYMOUS CLASS IMPLEMENTATION OF RUNNABLE ( run )");
				try {
					
					//1 thread fetches data
					//2 thread performs operations on it.
					
					
					/*2nd thread cant continue to execute
					 * until 1st thread is terminated
					  there wont be any data to process
					until 1 thread fetching the data has finished executing
					
					
					
					in this scenario rather than waking up thread periodically to see if there is any data
					we can join the second thread to the thread that is fetching the data 1
					
					second thread will first wait for the first thread to finish
					and then it will continue executing*/
					
					
					
					secondThread.join(11000);//CALL JOIN METHOD OF THE THREAD YOU WANT TO JOIN CURRENT THREAD TO.
					System.out.println("2.) 2ND THREAD TERMINATED HERE or TIMED OUT");
					System.out.println("5) ------------- EXITING FIFTH THREAD NOW , 5TH THREAD TERMINATED ------");
				}catch(InterruptedException exc) {
					System.out.println("5.) FIFTH THREAD INTERRUPTED");
					return;
				}
			}
		});
		
		
		fifthThreadRunnable.start();
		
		//secondThread.interrupt();
		
		/*
		 * if we call interrupt, INTERRUPTED EXCEPTION IS THROWN
		 */
		
		System.out.println("1) ------------- MAIN THREAD FINISHED ------");
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
