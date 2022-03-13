package deadlock;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//reading and writing messages
		//2 threads
		//1 produces messages
		//2 consumes messages
		Message message = new Message();
        (new Thread(new Writer(message))).start();//producer
        (new Thread(new Reader(message))).start();//consumer
	}

}

class Message{
	private String message;
	private boolean empty = true;
	
	public synchronized String read() {
		//used by consumer to read message
		while(empty) {
			
		}
		
		empty = true;//no message to read
		return message;
	}
	
	
	public synchronized void write(String message) {
		// used by producer to write message
		while(!empty) {
			
		}
		empty = false;
		this.message = message;
	}
}

//producer
class Writer implements Runnable{
	private Message message;
	
	public Writer(Message message) {
		this.message = message;
	}
	
	public void run() {
		String messages[] = {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the King's Horses and all the king's men",
                "Couldn't Put Humpty Together Again"

    };

		Random random = new Random();
        for(int i=0;i<messages.length;i++){
            //once we reach this loop.. Writer class keeps on looping and gets stuck in the write method
            //The value of empty is never changed
            //The Thread is blocked
            //This is called deadlock
            message.write(messages[i]);
            try{
                Thread.sleep(random.nextInt(2000));
            }catch(InterruptedException e){
                 
            }
        }
        message.write("Finished");
	}
}

//consumer
class Reader implements Runnable{
	 private Message message;

	    public Reader(Message message){
	        this.message = message;
	    }

	    @Override
	    public void run() {
	        Random random = new Random();
	        //initialize - test - counter
	        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read() ){
	            System.out.println(latestMessage);
	            //Writer executed and wrote to the message variable
	            //this loop prints out the message
	            //currently Thread 1 is sleeping
	            //after the try block below - this Reader Thread will also sleep
	            try{
	                Thread.sleep(random.nextInt(2000));
	            }catch(InterruptedException e){

	            }
	        }

	    }
}