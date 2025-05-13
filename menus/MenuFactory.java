package menus;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import shiftManager.MainProgram;

public class MenuFactory implements Creator {

	@Override
	public Menu factoryMethod() {
		switch (MainProgram.employee.getRole()) {
		case SHIFT_MANAGER:
		case MANAGER:
			return new ManagerMenu();
		case EMPLOYEE:
			return new EmployeeMenu();
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
		}
		return null;
	}
}
