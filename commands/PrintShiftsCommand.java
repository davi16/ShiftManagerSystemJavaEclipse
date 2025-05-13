package commands;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.EmployeeRoles;
import implimentation.ShiftManagementReciever;
import shiftManager.MainProgram;

public class PrintShiftsCommand implements Command {

	private EmployeeRoles myRole;
	private ShiftManagementReciever reciever;
	private char printChoice;

	public PrintShiftsCommand(EmployeeRoles role) {
		myRole = role;
		reciever = new ShiftManagementReciever();
	}

	@Override
	public <T> T execute() {
		System.out.println("Search Shifts by: 1.Day 2.Week 3.Month");
		printChoice = MainProgram.input.nextLine().charAt(0);
		while (printChoice > '3' || printChoice < '1') {
			System.out.println("Wrong Input, Try again");
			printChoice = MainProgram.input.nextLine().charAt(0);
		}
		reciever.printShifts(printChoice, myRole);
		return null;
	}

}
