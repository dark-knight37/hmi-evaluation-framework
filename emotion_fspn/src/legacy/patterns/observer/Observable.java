package legacy.patterns.observer;

public interface Observable {
	
	public void attach(Observer o);
	
	public Object getState();
	
	public void notifyObserver();
}
