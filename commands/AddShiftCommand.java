package commands;
//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921

import implimentation.ShiftManagementReciever;

public class AddShiftCommand implements Command {

	private ShiftManagementReciever reciever;

	public AddShiftCommand() {
		this.reciever = new ShiftManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.addShift();
		return null;
	}

}
