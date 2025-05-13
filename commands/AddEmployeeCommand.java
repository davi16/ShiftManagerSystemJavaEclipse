package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import implimentation.EmployeeManagementReciever;

public class AddEmployeeCommand implements Command {

	private EmployeeManagementReciever reciever;

	public AddEmployeeCommand() {
		this.reciever = new EmployeeManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.addEmployee();
		return null;
	}

}
