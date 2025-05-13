package menus;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import commands.AddShiftCommand;
import commands.AssignShiftCommand;
import commands.Command;
import commands.DeleteShiftCommand;
import commands.NotifyEmployeeCommand;
import commands.PrintShiftsCommand;
import commands.SearchEmployeeCommand;
import commands.SearchShiftCommand;
import commands.UpdateShiftCommand;
import employee.Employee;
import employee.EmployeeRoles;
import shift.Shift;
import shiftManager.MainProgram;

public class ShiftManagementMenu implements Menu {

	private List<String> options;
	private char choice;
	private EmployeeRoles employeeRole;
	private String name;

	public ShiftManagementMenu(EmployeeRoles role) {
		employeeRole = role;
		options = new ArrayList<String>();
		name = MainProgram.ANSI_BLUE + MainProgram.ANSI_BOLD + "	===Shift Management Menu==="
				+ MainProgram.ANSI_RESET;
		options.add("Assign Shift to Employee");
		options.add("Search Shift");
		if (role.equals(EmployeeRoles.MANAGER))
			options.add("Add Shift");
		options.add("Update Shift");
		if (role.equals(EmployeeRoles.MANAGER))
			options.add("Delete Shift");
		options.add("Print Shifts");
		options.add("Back to Main menu");
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
			option2();
			display();
			break;
		case '3':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				option4();
				display();
				break;
			}
			option3();
			display();
			break;
		case '4':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				option6();
				display();
				break;
			}
			option4();
			display();
			break;
		case '5':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				break;
			}
			option5();
			display();
			break;
		case '6':
			if (employeeRole.equals(EmployeeRoles.SHIFT_MANAGER)) {
				System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
				display();
				break;
			}
			option6();
			display();
			break;
		case '7':
			break;
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
			display();
		}
	}

	private void option1() {
		Employee e;
		Shift s;
		int numNotiSent;
		boolean isAssign;
		Command searchShift = new SearchShiftCommand();
		Command searchEmployee = new SearchEmployeeCommand();
		MainProgram.commandManager.setCommand(searchShift);
		s = MainProgram.commandManager.executeCommand();
		if (s != null) {
			MainProgram.commandManager.setCommand(searchEmployee);
			e = MainProgram.commandManager.executeCommand();
			if (e != null) {
				Command assign = new AssignShiftCommand(s, e);
				MainProgram.commandManager.setCommand(assign);
				isAssign = MainProgram.commandManager.executeCommand();
				if (isAssign) {
					Set<Employee> employee = new HashSet<Employee>();
					employee.add(e);
					Command notifyEmp = new NotifyEmployeeCommand(employee, s, 1);
					MainProgram.commandManager.setCommand(notifyEmp);
					numNotiSent = MainProgram.commandManager.executeCommand();
					System.out.println(
							MainProgram.ANSI_GREEN + numNotiSent + " employees were notified" + MainProgram.ANSI_RESET);
				}
			}
		}
	}

	private void option2() {
		Command search = new SearchShiftCommand();
		MainProgram.commandManager.setCommand(search);
		MainProgram.commandManager.executeCommand();
	}

	private void option3() {
		Command add = new AddShiftCommand();
		MainProgram.commandManager.setCommand(add);
		MainProgram.commandManager.executeCommand();
	}

	private void option4() {
		Shift s;
		int numNotiSent;
		Command search = new SearchShiftCommand();
		MainProgram.commandManager.setCommand(search);
		s = MainProgram.commandManager.executeCommand();
		if (s != null) {
			Command update = new UpdateShiftCommand(s);
			MainProgram.commandManager.setCommand(update);
			MainProgram.commandManager.executeCommand();
			Command notifyEmp = new NotifyEmployeeCommand(s.getShiftEmployees(), s, 2);
			MainProgram.commandManager.setCommand(notifyEmp);
			numNotiSent = MainProgram.commandManager.executeCommand();
			System.out.println(
					MainProgram.ANSI_GREEN + numNotiSent + " employees were notified" + MainProgram.ANSI_RESET);
		}
	}

	private void option5() {
		Shift s;
		int numNotiSent;
		Command search = new SearchShiftCommand();
		MainProgram.commandManager.setCommand(search);
		s = MainProgram.commandManager.executeCommand();
		if (s != null) {
			Command delete = new DeleteShiftCommand(s);
			MainProgram.commandManager.setCommand(delete);
			MainProgram.commandManager.executeCommand();
			Command notifyEmp = new NotifyEmployeeCommand(s.getShiftEmployees(), s, 2);
			MainProgram.commandManager.setCommand(notifyEmp);
			numNotiSent = MainProgram.commandManager.executeCommand();
			System.out.println(
					MainProgram.ANSI_GREEN + numNotiSent + " employees were notified" + MainProgram.ANSI_RESET);
		}
	}

	private void option6() {
		Command print = new PrintShiftsCommand(MainProgram.employee.getRole());
		MainProgram.commandManager.setCommand(print);
		MainProgram.commandManager.executeCommand();
	}
}
