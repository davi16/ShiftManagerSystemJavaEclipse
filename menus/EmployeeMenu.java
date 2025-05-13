package menus;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import commands.Command;
import commands.PrintShiftsCommand;
import employee.Employee;
import employee.EmployeeRoles;
import interfaces.CheckInOutManagement;
import shiftManager.CheckInOutRecord;
import shiftManager.MainProgram;

public class EmployeeMenu implements Menu, CheckInOutManagement {
	protected List<String> options;
	protected String name;
	protected char choice;
	protected CheckInOutRecord newRecord;

	public EmployeeMenu() {
		name = MainProgram.ANSI_BLUE + MainProgram.ANSI_BOLD + "	===Employee Menu===" + MainProgram.ANSI_RESET;
		options = new ArrayList<String>();
		options.add("Enter Shift");
		options.add("Exit Shift");
		options.add("Print My Records");
		options.add("Print My Shifts");
		if (MainProgram.employee.getRole().equals(EmployeeRoles.EMPLOYEE))
			options.add("Exit Program");
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
			checkIn(MainProgram.employee);
			break;
		case '2':
			checkOut(MainProgram.employee);
			break;
		case '3':
			printRecord();
			break;
		case '4':
			printEmployeeShifts();
			break;
		case '5':
			optionExit();
			break;
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
		}
	}

	@Override
	public void checkIn(Employee employe) {
		newRecord = new CheckInOutRecord();
		newRecord.setInRecord(LocalDateTime.now());
		newRecord.setOutRecord(null);
		employe.addRecord(newRecord);
		System.out.println(MainProgram.ANSI_GREEN + "Shift entered successfully!" + MainProgram.ANSI_RESET);
	}

	@Override
	public void checkOut(Employee employe) {
		newRecord.setOutRecord(LocalDateTime.now());
		System.out.println(MainProgram.ANSI_GREEN + "Shift exited successfully!" + MainProgram.ANSI_RESET);
	}

	@Override
	public void printRecord() {
		if (MainProgram.employee.getRecords().isEmpty()) {
			System.out.println(MainProgram.ANSI_RED + "No records found" + MainProgram.ANSI_RESET);
			return;
		}
		System.out.println(MainProgram.ANSI_CYAN + "your records are: " + MainProgram.ANSI_RESET);
		for (CheckInOutRecord rec : MainProgram.employee.getRecords())
			System.out.println(rec);
		System.out.println();
	}

	protected void printEmployeeShifts() {
		Command print = new PrintShiftsCommand(EmployeeRoles.EMPLOYEE);
		MainProgram.commandManager.setCommand(print);
		MainProgram.commandManager.executeCommand();

	}

	protected void optionExit() {
		MainProgram.isMenu = false;
		System.out.println("Press any key to get back into system (enter the string \"stop\" to end the program)");
	}
}