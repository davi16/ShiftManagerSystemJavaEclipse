package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;
import implimentation.EmployeeManagementReciever;

public class DeleteEmployeeCommand implements Command {

	private EmployeeManagementReciever reciever;
	private Employee e;

	public DeleteEmployeeCommand(Employee e) {
		this.e = e;
		this.reciever = new EmployeeManagementReciever();
	}

	@Override
	public <T> T execute() {
		reciever.deleteEmployee(e);
		return null;
	}
}
