package ex4;

public abstract class Door {	
	public abstract void open();
	public abstract void close();	
}

interface Alarm{ 
	void alarm(); 
} 

class AlarmDoor extends Door implements Alarm{ 
	public void open(){		
	} 
	public void close(){		
	} 
	public void alarm(){		
	} 
} 
