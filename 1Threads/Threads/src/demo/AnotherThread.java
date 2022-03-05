package demo;

public class AnotherThread extends Thread{

	@Override
	public void run() {
		System.out.println("2.) ------------ 2ND THREAD -------------------------------");
		try {
			Thread.sleep(10000);
		} catch(InterruptedException exc) {
			System.out.println("2ND THREAD INTERRUPTED EXCEPTION");
			return;//THIS IS HOW WE TERMINATE THE SECOND THREAD ( ANOTHER THREAD INSTANCE ANYWHERE WHEN INTERRUPT IS CALLED IN THE MAIN THREAD OR CALLING THREAD)
			
		}
		System.out.println("2ND THREAD CONTINUE EXECUTION");	
	}

	
	
}
