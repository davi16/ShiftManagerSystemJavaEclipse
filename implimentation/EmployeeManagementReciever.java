package implimentation;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDate;
import employee.Employee;
import employee.EmployeeDatabase;
import employee.EmployeeRoles;
import interfaces.EmployeeManagement;
import shiftManager.InputCheck;
import shiftManager.MainProgram;
import user.UserDataBase;

public class EmployeeManagementReciever implements EmployeeManagement {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T searchEmployee() {
		String id;
		Employee employee;
		System.out.println("Enter employee ID number:");
		id = InputCheck.checkID(MainProgram.input , true);
		employee = EmployeeDatabase.getInstance().getEmployeeByID(id);
		if (employee != null)
			System.out.println(employee);
		else
			System.out.println(MainProgram.ANSI_RED + "Employee not found" + MainProgram.ANSI_RESET);
		return (T) employee;
	}

	@Override
	public void addEmployee() {
		String ID, firstName, lastName, username, password;
		LocalDate birthDate;
		EmployeeRoles newRole;
		int rank;
		System.out.println("Enter employee values:");
		System.out.printf("ID number: ");
		ID = InputCheck.checkID(MainProgram.input , false);
		System.out.printf("First name: ");
		firstName = InputCheck.checkName(MainProgram.input);
		System.out.printf("Last name: ");
		lastName = InputCheck.checkName(MainProgram.input);
		System.out.printf("Birth Date (dd.MM.yy): ");
		birthDate = InputCheck.scanToLocalDate(MainProgram.input);
		System.out.printf("Username: ");
		username = InputCheck.checkUserName(MainProgram.input);
		System.out.printf("Emloyee role (e-employee, s-shift manager, m-manager): ");
		newRole = InputCheck.checkRole(MainProgram.input);
		System.out.print("Rank: ");
		rank = InputCheck.checkRank(MainProgram.input);
		Employee e = new Employee(ID, firstName, lastName, birthDate, newRole, rank);
		EmployeeDatabase.getInstance().addEmployee(e);
		e.setUsername(username);
		System.out.printf("Enter password for employee: ");
		password = MainProgram.input.nextLine();
		UserDataBase.getInstance().addUser(username, password);
		System.out.println(MainProgram.ANSI_GREEN + e + " Added successfully!" + MainProgram.ANSI_RESET);
	}

	@Override
	public void updateEmployee(Employee employee) {
		System.out.println("What would you like to update?");
		System.out.println("1.First name 2.Last name 3.Role 4.Rank");
		char choice;
		boolean finish = false;
		while (!finish) {
			choice = MainProgram.input.nextLine().charAt(0);
			switch (choice) {
			case '1':
				System.out.print("Enter new first name:");
				String fname = InputCheck.checkName(MainProgram.input);
				employee.setFirstName(fname);
				finish = true;
				break;
			case '2':
				System.out.print("Enter new last name:");
				String lname = InputCheck.checkName(MainProgram.input);
				employee.setLastName(lname);
				finish = true;
				break;
			case '3':
				System.out.print("Enter new employee role (e-employee, s-shift manager, m-manager):");
				EmployeeRoles role = InputCheck.checkRole(MainProgram.input);
				employee.setRole(role);
				finish = true;
				break;
			case '4':
				System.out.print("Enter new employee rank:");
				int rank = InputCheck.checkRank(MainProgram.input);
				employee.setRank(rank);
				finish = true;
				break;
			default:
				System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
			}
		}

	}

	@Override
	public void deleteEmployee(Employee employee) {
		EmployeeDatabase.getInstance().deleteEmployee(employee);

	}

	@Override
	public void printEmployees() {
		for (Employee e : EmployeeDatabase.getInstance().getEmployees())
			System.out.println(e);
	}

}
