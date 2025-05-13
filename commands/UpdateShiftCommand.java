package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import implimentation.ShiftManagementReciever;
import shift.Shift;

public class UpdateShiftCommand implements Command {

	private ShiftManagementReciever reciever;
	private Shift s;

	public UpdateShiftCommand(Shift s) {
		this.s = s;
		reciever = new ShiftManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.updateShift(s);
		return null;
	}

}
