package shiftManager;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.Scanner;

import commands.AddEmployeeCommand;
import commands.Command;
import commands.ShiftInvoker;
import employee.Employee;
import employee.EmployeeDatabase;
import menus.Menu;
import menus.MenuFactory;
import user.*;

public class MainProgram {

	public static final String ANSI_BLUE = "\u001B[34m"; // Console blue text color
	public static final String ANSI_RED = "\u001B[31m"; // Console red text color
	public static final String ANSI_GREEN = "\033[1;32m"; // Console green + BOLD text color
	public static final String ANSI_RESET = "\u001B[0m"; // Console black text color
	public static final String ANSI_BOLD = "\u001B[1m"; // Console bold text
	public static final String ANSI_CYAN = "\033[0;36m";

	public static Scanner input = new Scanner(System.in); // Input scanner from console
	public static Employee employee;
	public static boolean isMenu;
	public static ShiftInvoker commandManager = new ShiftInvoker();

	public static void main(String[] args) {
		String stop = "start";
		while (stop.toLowerCase().compareTo("stop") != 0) {
			isMenu = true;
			if (EmployeeDatabase.getInstance().getEmployees().isEmpty()) {
				Command add = new AddEmployeeCommand();
				commandManager.setCommand(add);
				commandManager.executeCommand();
			}
			UserAuthenticator authenticator = new SimpleUserAuthenticator();
			String username, password;
			System.out.print("Username: ");
			username = input.nextLine().trim();
			System.out.print("Password: ");
			password = input.nextLine().trim();
			while (!authenticator.authenticate(username, password)) {
				System.out.println(ANSI_RED + "The authentication FAILED, Try again" + ANSI_RESET);
				System.out.print("Username: ");
				username = input.nextLine().trim();
				System.out.print("Password: ");
				password = input.nextLine().trim();
			}

			System.out.println(ANSI_GREEN + "The authentication was succesesful" + ANSI_RESET);
			employee = EmployeeDatabase.getInstance().getEmployeeByUser(username);
			MenuFactory menuFactory = new MenuFactory();
			Menu userMenu = menuFactory.factoryMethod();
			while (isMenu)
				userMenu.display();
			stop = input.nextLine().trim();
		}

	}
}
