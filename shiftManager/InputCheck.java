package shiftManager;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TooManyListenersException;

import employee.EmployeeDatabase;
import employee.EmployeeRoles;
import user.UserDataBase;

public class InputCheck {

	static final String ANSI_BLUE = "\u001B[34m"; // Console blue text color
	static final String ANSI_RED = "\u001B[31m"; // Console red text color
	static final String ANSI_GREEN = "\033[1;32m"; // Console green + BOLD text color
	static final String ANSI_RESET = "\u001B[0m"; // Console black text color
	static final String ANSI_BOLD = "\u001B[1m"; // Console bold text

	public static String checkID(Scanner input, boolean ignore) {
		String newID = "";
		boolean finished = false;
		while (!finished) {
			try {
				newID = input.nextLine().trim();
				if (EmployeeDatabase.getInstance().getEmployeeByID(newID) != null && !ignore)
					throw new TooManyListenersException();
				if (!newID.matches("[0-9]+"))
					throw new IllegalCallerException();
				if (newID.length() > 9 || newID.length() == 0)
					throw new RuntimeException();
				finished = true;
			} catch (TooManyListenersException a) {
				System.out.println(ANSI_RED + "Employee with that ID already exists, try again." + ANSI_RESET);
				System.out.print("Enter ID number again: ");
			} catch (IllegalCallerException e) {
				System.out.println(ANSI_RED + "Invalid characters entered, try again." + ANSI_RESET);
				System.out.print("Enter ID number again: ");
			} catch (RuntimeException b) {
				System.out.println(ANSI_RED + "ID length not legal, try again." + ANSI_RESET);
				System.out.print("Enter ID number again: ");
			}
		}

		return newID;
	}

	public static LocalDate scanToLocalDate(Scanner input) {
		String dateS = MainProgram.input.nextLine().trim();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			LocalDate date = LocalDate.parse(dateS, formatter);
			if (date.isAfter(LocalDate.now().plusYears(1)))
				date = date.minusYears(100);
			return date;
		} catch (Exception e) {
			System.out.println("Wrong date input, Try again");
			return scanToLocalDate(MainProgram.input);
		}
	}

	public static LocalTime scanToLocalTime(Scanner input) {
		String timeS = MainProgram.input.nextLine().trim();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			return LocalTime.parse(timeS, formatter);
		} catch (Exception e) {
			System.out.println("Wrong time input, Try again");
			return scanToLocalTime(MainProgram.input);
		}
	}

	public static String checkName(Scanner input) {
		String newName = "";
		boolean finished = false;
		while (!finished) {
			try {
				newName = input.nextLine().trim();

				if (!newName.matches("[a-zA-Z| |-]+"))
					throw new IllegalCallerException();
				finished = true;
			} catch (IllegalCallerException e) {
				System.out.println(ANSI_RED + "Invalid characters entered, try again." + ANSI_RESET);
				System.out.print("Enter name again: ");
			}
		}
		return capitalize(newName);
	}

	private static final String capitalize(String str) {
		// Capitalizes all the character in input String
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String checkUserName(Scanner input) {
		String newUserName = "";
		boolean finished = false;
		while (!finished) {
			try {
				newUserName = input.nextLine().trim();
				if (UserDataBase.getInstance().isUser(newUserName))
					throw new IllegalCallerException();
				finished = true;
			} catch (IllegalCallerException e) {
				System.out.println(ANSI_RED + "Username already exists, try again." + ANSI_RESET);
				System.out.print("Enter another username again: ");
			}
		}
		return newUserName;
	}

	public static EmployeeRoles checkRole(Scanner input) {
		char newRole;
		EmployeeRoles role = null;
		boolean finished = false;
		while (!finished) {
			try {
				newRole = input.nextLine().trim().toLowerCase().charAt(0);
				switch (newRole) {
				case 'e':
					role = EmployeeRoles.EMPLOYEE;
					finished = true;
					break;
				case 's':
					role = EmployeeRoles.SHIFT_MANAGER;
					finished = true;
					break;
				case 'm':
					role = EmployeeRoles.MANAGER;
					finished = true;
					break;
				default:
					throw new IllegalCallerException();
				}
			} catch (IllegalCallerException e) {
				System.out.println(ANSI_RED + "Wrong input, try again." + ANSI_RESET);
				System.out.print("Enter employee role again (e/s/m): ");
			}
		}
		return role;
	}

	public static int checkRank(Scanner input) {
		int newRank = 0;
		boolean finished = false;
		while (!finished) {
			try {
				newRank = input.nextInt();
				if (newRank < 1 || newRank > 5)
					throw new IllegalCallerException();
				finished = true;
			} catch (IllegalCallerException e) {
				System.out.println(ANSI_RED + "Rank not in range, try again." + ANSI_RESET);
				System.out.print("Enter rank again (1-5): ");
				input.nextLine();
			}catch (InputMismatchException a) {
				System.out.println(ANSI_RED + "Invalid character entered, try again." + ANSI_RESET);
				System.out.print("Enter rank again (1-5): ");
				input.nextLine();
			}
		}
		input.nextLine();
		return newRank;
	}
}
