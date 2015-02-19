package multithreading2;

public class Haushaltskasse 
{
    private static int saldo;
    
    public static void setSaldo(int s)
    {
	saldo=s;
    }
    
    public static int getSaldo()
    {
	return saldo;
    }
    
    public static void addGeld(int summe)
    {
	saldo+=summe;
    }
    
    
    
    
    
}
