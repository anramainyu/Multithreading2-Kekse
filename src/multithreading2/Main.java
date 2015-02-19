package multithreading2;
/**
 * 	MULTITHREADING UND KONKURRIERENDE ZUGRIFFE
 * 
 * 		-> Zugriffe mehrerer Threads auf gemeinsame Speicherbereiche k�nnen
 * 		  zu schweren Inkonsistenzen f�hren
 * 
 * 		-> Konsequenz: eine Klasse, welche threadf�hig ist und kritische
 * 		   Datenoperationen durchf�hrt, muss so entwickelt werden, dass sie
 * 		   threadsicher(threadsafe) ist!
 * 
 * 		-> Das Konzept des "Monitors" erlaubt es, bestimmte Codebereiche
 * 		   atomar zu halten:
 * 			-> Wenn ein bestimmter Thread diesen Bereich momentan
 * 			   abarbeitet, sind alle anderen solange ausgesperrt, bis der
 * 			   Thread den Bereich verlassen hat
 * 
 * 			-> Monitoring erlaubt es zudem, Speicherbereiche transparent zu
 * 			   halten....das bedeutet, dass alle relevanten Threads stets
 * 			   dar�ber informiert sind(�ber spezielle Speicheroperationen),
 * 			   welchen Status ein bestimmter Speicherbereich momentan hat
 * 
 * 			-> Eine Sperre eines Threads nennt man "Lock"
 * 
 * 		-> Ein Lock geh�rt immer zu genau einem konkreten Thread
 * 
 * 		-> Locks beziehen sich IMMER nur auf Objekte! -> Sie k�nnen NICHT
 * 		   auf primitive Variablen oder zum Beispiel lokale Referenzvariablen
 * 		   gesetzt werden
 * 
 * 		-> Wenn klassenbezogene Elemente(static) atomar sein sollen, muss sich
 * 		   der Lock auf das zugeh�rige Klassenobjekt beziehen(Klassennname.class)
 * 
 * 		-> In Java wird ein Lock mit dem Schl�sselwort synchronized eingeleitet
 * 
 * 		-> Ein Thread kann mehrere Locks halten(auf mehrere Instanzen)
 * 
 * 		-> Wenn ein Thread per sleep() schlafen gelegt wird, wird sein Lock
 * 		   trotzdem aufrecht erhalten!
 * 
 * 		-> Man kann in Java per synchronized auch ganze Methoden atomar machen
 * 
 * 			public synchronized void methodeA(){ ... }
 * 
 * 		-> Die Gr��e der Codebereiche, welche per synchronized belegt werden, 
 * 		   sollten sehr gut gew�hlt, so klein wie m�glich und NUR AUF WIRKLICH
 * 		   KRITISCHE OPERATIONEN bezogen sein!
 * 
 * 		-> Tritt in einem synchronized-Block eine Excpetion auf, wird der
 * 		   entsprechende Lock aufgehoben(Weil der Codebereich ja verlassen wird)
 * 
 * 		-> Wenn wirklich viele Objekte gelockt werden sollen, lohnt es sich
 * 		   unter Umst�nden ein separates Objekt anzulegen, in welchem sich die
 * 		   zu lockenden Objekte befinden
 * 
 * 		-> Threads k�nnen sich untereinander benachrichtigen(Signale senden)
 * 
 * 			-> Mit den Methoden der Klasse Object:
 * 
 * 				- wait(), notify() und notifyAll()			
 * 
 * 			-> wait()
 * 
 * 				-> veranlasst den current Thread, mit seiner Ausf�hrung
 * 				   solange zu warten, bis er von einem anderen Thread
 * 				   (welcher ebenfalls einen Lock auf dasselbe Objekt
 * 				   h�lt) per notify() bzw. notifyAll() benachrichtigt
 * 				   wird
 * 
 * 			-> notify()
 * 
 * 				-> In einer Warteschliefe, in welcher Threads auf ein
 * 			   	   Signal warten, wird GENAU EINER benachrichtigt
 * 
 * 			-> notifyAll()
 * 			
 * 				-> In einer Warteschleife, werden ALLE Threads 
 * 				   benachritigt
 * 
 * 	-> Diese 3 Methoden d�rfen NUR in synchronized-Bl�cken aufgerufen werden!
 * 
 * 
 * 
 * 
 * 
 * 
 *
 */





public class Main 
{

    
    private Integer zahl;
    private String name;
    private Object o;
    private volatile int wert;
    
    public static void main(String[] args) 
    {
	/*
	new Thread(new Zaehler(), "Thread A").start();
	new Thread(new Zaehler(), "Thread B").start();
	*/
	
	synchronized (Main.class) 
	{
	   
	}
	
	
	new Thread(new Mann("Klaus"), "Klaus").start();
	new Thread(new Frau("Gabi"), "Gabi").start();
	
	
	
	
	
	
	
	

    }
    
    //da diese methode objektgebunden ist und sich in der klasse Main befindet, wird beim betretet
    //dieser methode durch einen Thread ein Lock auf das Objekt der Klasse Main gesetzt
    public synchronized void berechnung()
    {
	
    }
    
    
    //beim betreten dieser klassenmethode wird ein lock auf das klassenobjekt Main.class gelegt
    public synchronized static int getZahl()
    {
	return 0;
    }
    
    public void verschachtelteBloecke()
    {
	synchronized (Main.class) 
	{
	    synchronized (name) 
	    {
		
	    }
	}
    }
    
    //VORSICHT: Wenn ein Thread A momentan eine Sperre in der Methode verschachtelteBlocecke() h�lt
    //und parallel dazu ein Thread B in der hier vorliegenden Methode einen Lock h�lt, kann es zu
    //einem Deadlock kommen!!!!
    public void verschachteleBloeckeAnti()
    {
	synchronized (name) 
	{
	    synchronized (Main.class) 
	    {
		
	    }
	}
    }
    
    
    

}
