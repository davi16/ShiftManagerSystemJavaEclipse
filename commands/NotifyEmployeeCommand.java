package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.Set;

import employee.Employee;
import shift.Shift;
import shiftNotifier.AssignShiftNotifier;
import shiftNotifier.UpdateShiftNotifier;

public class NotifyEmployeeCommand implements Command {

	private Set<Employee> employees;
	private Shift shift;
	private AssignShiftNotifier notifier1;
	private UpdateShiftNotifier notifier2;
	private int changeType;

	public NotifyEmployeeCommand(Set<Employee> employees, Shift shift, int changeType) {
		this.employees = employees;
		this.shift = shift;
		this.changeType = changeType;
		if (changeType == 1)
			notifier1 = new AssignShiftNotifier();
		if (changeType == 2)
			notifier2 = new UpdateShiftNotifier();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer execute() {
		if (changeType == 1) {
			notifier1.setShift(shift);
			for (Employee e : employees)
				notifier1.addObserver(e);
			notifier1.notifyAllObservers();
			return notifier1.countObservers();
		}
		if (changeType == 2) {
			notifier2.setShift(shift);
			for (Employee e : employees)
				notifier2.addObserver(e);
			notifier2.notifyAllObservers();
			return notifier2.countObservers();
		}
		return 0;
	}

}
