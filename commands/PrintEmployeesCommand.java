package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import implimentation.EmployeeManagementReciever;

public class PrintEmployeesCommand implements Command {

	private EmployeeManagementReciever reciever;

	public PrintEmployeesCommand() {
		this.reciever = new EmployeeManagementReciever();
	}

	@Override
	public <T> T execute() {
		System.out.println("The employees are:");
		reciever.printEmployees();
		return null;
	}

}
