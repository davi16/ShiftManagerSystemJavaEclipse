package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;
import implimentation.EmployeeManagementReciever;

public class UpdateEmployeeCommand implements Command {

	private EmployeeManagementReciever reciever;
	private Employee e;

	public UpdateEmployeeCommand(Employee e) {
		this.e = e;
		reciever = new EmployeeManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.updateEmployee(e);
		return null;
	}

}
