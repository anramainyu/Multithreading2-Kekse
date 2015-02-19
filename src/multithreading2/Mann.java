package multithreading2;

import java.util.Random;

public class Mann implements Runnable
{
    private Frau ehefrau;
    private Random zuffi=new Random();
    private String name;
    
    public Mann(String name)
    {
	this.name=name;
    }

    public void setFrau(Frau huebsch)
    {
	ehefrau=huebsch;
    }
    
    @Override
    public void run() 
    {
	while(true)
	{
	    synchronized(Haushaltskasse.class)
	    {
		
		//zufällig geld verdienen
		int geldVerdient=zuffi.nextInt(100);
		System.out.println(name + " verdiente " + geldVerdient);
		
		//das verdiente geld der kasse gutschreiben
		Haushaltskasse.addGeld(geldVerdient);
		System.out.println("In der Kasse sind nun " + Haushaltskasse.getSaldo() + " Euro.");
		
		//sobald in der kasse mehr als 200 euro drin sind, soll die frau benachrichtigt werden,
		//dass sie geld ausgeben kann
		if(Haushaltskasse.getSaldo()>200)
		{
		    Haushaltskasse.class.notify();
		    
		    try 
		    {
			Haushaltskasse.class.wait();//nun wartet der mann mit dem geld-verdienen solange,
						    //bis die frau ihn darüber benachrichtigt, dass der 
						    //stand der kass unter 20 euro liegt
		    }
		    catch (InterruptedException e) 
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    
		}
		
		try {
		    Thread.sleep(2500);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		
		
		
		
	    }
	    
	    
	    
	    
	    
	}
	
	
	
	
	
	
    }
    
    
    
    
    
    
    
}
