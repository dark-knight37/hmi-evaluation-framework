package legacy.patterns.observer;

public interface Observer {
	
	public void attach(Observable s);
	
	public void update();
}
