package Kekse;

import multithreading2.Haushaltskasse;

import java.util.Random;

/**
 * Created by papazong on 19.02.2015.
 */
public class Enkel implements Runnable {
    public String name;
    public Random zuffi;
    public String oma;

    public Enkel(String name)
    {
        this.name=name;
    }


    @Override
    public void run()
    {
        while(true)
        {
            synchronized (Keksdose.class)
            {
                if(Keksdose.getKekse()<=20)
                {
                    Haushaltskasse.class.notifyAll();
                    try
                    {
                        Haushaltskasse.class.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
                int kekseEssen=zuffi.nextInt(15)+5;
                System.out.println(name + " hat " + kekseEssen + " gegessen.");

                Keksdose.setKekse(Keksdose.getKekse() - kekseEssen);
                System.out.println("In der Keksdose sind nun " + Keksdose.getKekse() + " Kekse.");
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }
}
