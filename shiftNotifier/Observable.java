package shiftNotifier;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
public interface Observable {

	public void setChanged(boolean isChanged);

	public boolean isChanged();

	public void addObserver(Observer employee);

	public void removeObserver(Observer employee);

	public void notifyObserver(Observer employee);

	public void notifyAllObservers();

	public int countObservers();
}
