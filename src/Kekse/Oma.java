package Kekse;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by papazong on 19.02.2015.
 */
public class Oma implements  Runnable
{
    public String name;
    public Random zuffi=new Random();
    public ArrayList<Enkel> enkelArli;


    @Override
    public void run()
    {
        while(true)
        {
            synchronized (Keksdose.class)
            {
                if(Keksdose.getKekse()<=20)
                {
                    int kekseBacken=zuffi.nextInt(24);
                    System.out.println(name + " backte "+kekseBacken +"Kekse.");

                    // Kekse in die Keksedose legen
                    Keksdose.addKeks(kekseBacken);
                    System.out.println("Nun sind "+Keksdose.getKekse()+" Kekse in der Keksdose." );

                    if(Keksdose.getKekse()>=100)
                    {
                        Keksdose.class.notifyAll();
                        try
                        {
                            Keksdose.class.wait();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    Keksdose.class.notify();
                    try
                    {
                        Keksdose.class.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
