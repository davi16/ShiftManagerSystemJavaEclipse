package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;
import implimentation.EmployeeManagementReciever;

public class SearchEmployeeCommand implements Command {

	private EmployeeManagementReciever reciever;

	public SearchEmployeeCommand() {
		this.reciever = new EmployeeManagementReciever();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee execute() {
		return reciever.searchEmployee();
	}

}
