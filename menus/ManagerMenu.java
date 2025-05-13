package menus;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.EmployeeRoles;
import shiftManager.MainProgram;

public class ManagerMenu extends EmployeeMenu {

	public ManagerMenu() {
		if (MainProgram.employee.getRole().equals(EmployeeRoles.SHIFT_MANAGER))
			name = MainProgram.ANSI_BLUE + MainProgram.ANSI_BOLD + "    ===Shift Manager Menu==="
					+ MainProgram.ANSI_RESET;
		else if (MainProgram.employee.getRole().equals(EmployeeRoles.MANAGER))
			name = MainProgram.ANSI_BLUE + MainProgram.ANSI_BOLD + "    ===Manager Menu===" + MainProgram.ANSI_RESET;
		options.add("Show Shift Management Options");
		options.add("Show Employee Management Options");
		options.add("Exit Program");
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
			openShiftManagementMenu();
			break;
		case '6':
			openEmployeeManagementMenu();
			break;
		case '7':
			optionExit();
			break;
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
		}
	}

	public void openShiftManagementMenu() {
		ShiftManagementMenu menu = new ShiftManagementMenu(MainProgram.employee.getRole());
		menu.display();
	}

	public void openEmployeeManagementMenu() {
		EmployeeManagementMenu menu = new EmployeeManagementMenu(MainProgram.employee.getRole());
		menu.display();

	}

}
