package shiftNotifier;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import shift.Shift;

public class AssignShiftNotifier implements Observable {

	private boolean isChanged;
	private Shift shift;
	private List<Observer> observers;

	public AssignShiftNotifier() {
		this.isChanged = false;
		this.observers = new ArrayList<>();
	}

	@Override
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;

	}

	@Override
	public boolean isChanged() {
		return isChanged;
	}

	public void setShift(Shift shift) {
		if (shift != this.shift) {
			this.shift = shift;
			setChanged(true);
		}
	}

	public Shift getShift() {
		return shift;
	}

	@Override
	public void addObserver(Observer employee) {
		this.observers.add(employee);

	}

	@Override
	public void removeObserver(Observer employee) {
		this.observers.remove(employee);

	}

	@Override
	public void notifyObserver(Observer employee) {
		if (!isChanged)
			return;
		employee.notify(this);
		setChanged(false);

	}

	@Override
	public void notifyAllObservers() {
		if (!isChanged)
			return;
		for (Iterator<Observer> it = observers.iterator(); it.hasNext();) {
			Observer observer = (Observer) it.next();
			observer.notify(this);
		}
		setChanged(false);

	}

	@Override
	public int countObservers() {
		return observers.size();
	}

}
