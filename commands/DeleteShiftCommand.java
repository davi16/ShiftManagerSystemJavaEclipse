package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import implimentation.ShiftManagementReciever;
import shift.Shift;

public class DeleteShiftCommand implements Command {

	private ShiftManagementReciever reciever;
	private Shift s;

	public DeleteShiftCommand(Shift s) {
		this.s = s;
		this.reciever = new ShiftManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.deleteShift(s);
		return null;
	}

}
