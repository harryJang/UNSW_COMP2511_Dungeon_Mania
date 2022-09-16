package unsw.loopmania;

public interface Subject {
    public void notifyObservers();
    public void attach(Observer o);
	public void detach();
}
