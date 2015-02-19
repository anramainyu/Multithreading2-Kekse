package multithreading2;

import java.util.Random;

public class Frau implements Runnable
{
    private Mann ehemann;
    private Random zuffi=new Random();
    private String name;
    
    public Frau(String name)
    {
	this.name=name;
    }
    
    public void setMann(Mann m)
    {
	ehemann=m;
    }

    @Override
    public void run() 
    {
	while(true)
	{
	    synchronized(Haushaltskasse.class) 
	    {
		//sobald das konto unter 20 euro fällt, soll der mann arbeiten gehen
		if(Haushaltskasse.getSaldo()<20)
		{
		    
		    Haushaltskasse.class.notify();
		    
		    try 
		    {
			Haushaltskasse.class.wait();
		    } 
		    catch (InterruptedException e) 
		    {
			e.printStackTrace();
		    }
		}
		
		//zufällige menge geld aus der klasse nehmen....
		int geldAusgeben=zuffi.nextInt(50);
		System.out.println(name + " gab " + geldAusgeben + " Euro aus.");
		
		Haushaltskasse.setSaldo(Haushaltskasse.getSaldo()-geldAusgeben);
		System.out.println("In der Kasse sind nun " + Haushaltskasse.getSaldo() + " Euro übrig.");
		
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
