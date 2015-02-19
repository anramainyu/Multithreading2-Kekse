package multithreading2;

public class Zaehler implements Runnable
{

    static int wert;
    static int wert2;
    
    
    @Override
    public void run() 
    {
	
	while(true)
	{
	    synchronized(Zaehler.class)
	    {
		wert++;
		System.out.println(Thread.currentThread().getName() + " : " + wert);
		//sobald der aktuelle thread hochgezählt und ausgegeben hat, soll der
		//andere drankommen, welcher wartet
		Zaehler.class.notify();
		
		try {
		    Zaehler.class.wait();
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		
		
	    }
	     
	    try {
		Thread.sleep(900);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
	
    }
    
    
    
    
    
}
