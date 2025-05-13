package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import implimentation.ShiftManagementReciever;
import shift.Shift;

public class SearchShiftCommand implements Command {

	private ShiftManagementReciever reciever;

	public SearchShiftCommand() {
		this.reciever = new ShiftManagementReciever();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Shift execute() {
		return reciever.searchShift();
	}

}
