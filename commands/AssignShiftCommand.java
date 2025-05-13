package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;
import implimentation.ShiftManagementReciever;
import shift.Shift;

public class AssignShiftCommand implements Command {

	private ShiftManagementReciever reciever;
	private Shift s;
	private Employee e;

	public AssignShiftCommand(Shift s, Employee e) {
		reciever = new ShiftManagementReciever();
		this.e = e;
		this.s = s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean execute() {
		return reciever.assignShift(s, e);
	}

}
