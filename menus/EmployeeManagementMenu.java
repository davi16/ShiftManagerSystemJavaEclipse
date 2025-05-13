package menus;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.ArrayList;
import java.util.List;

import commands.AddEmployeeCommand;
import commands.Command;
import commands.DeleteEmployeeCommand;
import commands.PrintEmployeesCommand;
import commands.SearchEmployeeCommand;
import commands.UpdateEmployeeCommand;
import employee.Employee;
import employee.EmployeeRoles;
import shiftManager.MainProgram;

public class EmployeeManagementMenu implements Menu {

	private List<String> options;
	private char choice;
	private EmployeeRoles employeeRole;
	private String name;

	public EmployeeManagementMenu(EmployeeRoles role) {
		employeeRole = role;
		options = new ArrayList<String>();
		name = MainProgram.ANSI_BLUE + MainProgram.ANSI_BOLD + "    ===Employee Management Menu==="
				+ MainProgram.ANSI_RESET;
		options.add("Search Employee");
		if (role.equals(EmployeeRoles.MANAGER))
			options.add("Add Employee");
		options.add("Update Employee");
		if (role.equals(EmployeeRoles.MANAGER))
			options.add("Delete Employee");
		options.add("Print Employees");
		options.add("Return to main menu");

	}

	@Override
	public void display() {
		int n = 1;
		System.out.println(name);
		for (String option : options) {
			System.out.println("\t" + n + ". " + option);
			n++;
		}
		enterChoice();
	}

	@Override
	public void enterChoice() {
		System.out.print("Enter your choice: ");
		choice = MainProgram.input.nextLine().charAt(0);
		switch (choice) {
		case '1':
			option1();
			display();
			break;
		case '2':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				option3();
				display();
				break;
			}
			option2();
			display();
			break;
		case '3':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				option5();
				display();
				break;
			}
			option3();
			display();
			break;
		case '4':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				break;
			}
			option4();
			display();
			break;
		case '5':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
				display();
				break;
			}
			option5();
			display();
			break;
		case '6':
			break;
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
			display();
		}
	}

	private void option1() {
		Command search = new SearchEmployeeCommand();
		MainProgram.commandManager.setCommand(search);
		MainProgram.commandManager.executeCommand();

	}

	private void option2() {
		Command add = new AddEmployeeCommand();
		MainProgram.commandManager.setCommand(add);
		MainProgram.commandManager.executeCommand();
	}

	private void option3() {
		Employee e;
		Command search = new SearchEmployeeCommand();
		MainProgram.commandManager.setCommand(search);
		e = MainProgram.commandManager.executeCommand();
		if (e != null) {
			Command update = new UpdateEmployeeCommand(e);
			MainProgram.commandManager.setCommand(update);
			MainProgram.commandManager.executeCommand();
		}
	}

	private void option4() {
		Employee e;
		Command search = new SearchEmployeeCommand();
		MainProgram.commandManager.setCommand(search);
		e = MainProgram.commandManager.executeCommand();
		if (e != null) {
			Command delete = new DeleteEmployeeCommand(e);
			MainProgram.commandManager.setCommand(delete);
			MainProgram.commandManager.executeCommand();
		}
	}

	private void option5() {
		Command print = new PrintEmployeesCommand();
		MainProgram.commandManager.setCommand(print);
		MainProgram.commandManager.executeCommand();
	}

}
