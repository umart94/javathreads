package deadlocksolution;

import java.util.Random;



public class DeadLockSolution {

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
			try {
				wait();
			}catch(InterruptedException e) {
				
			}
			
		}
		
		empty = true;//no message to read
		notifyAll();
		return message;
	}
	
	
	public synchronized void write(String message) {
		// used by producer to write message
		while(!empty) {
			try {
				wait();
			}catch(InterruptedException e) {
				
			}
		}
		empty = false;
		this.message = message;
		notifyAll();
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
	      
	        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read() ){
	            System.out.println(latestMessage);
	        
	            try{
	                Thread.sleep(random.nextInt(2000));
	            }catch(InterruptedException e){

	            }
	        }

	    }
}
