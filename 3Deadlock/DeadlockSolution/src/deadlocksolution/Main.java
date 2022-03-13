package deadlocksolution;

public class Main {

	public static void main(String[] args) {
	
		/*
		 when a thread calls wait method it will suspend execution
		 and release whatever lock it is holding until
		 another thread issues a notification that something 
		 important has happened
		 
		 the other thread does this by calling notify and notifyAll
		 
		 
		 call wait method inside read and write method.
		 
		 and call notifyAll method inside each thread.
		 after it has changed value of empty variable.
		 */

	}

}
