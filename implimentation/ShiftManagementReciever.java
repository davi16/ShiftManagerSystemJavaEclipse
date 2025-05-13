package implimentation;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import employee.Employee;
import employee.EmployeeDatabase;
import employee.EmployeeRoles;
import interfaces.ShiftManagment;
import shift.Shift;
import shift.ShiftDataBase;
import shiftManager.InputCheck;
import shiftManager.MainProgram;

public class ShiftManagementReciever implements ShiftManagment {

	@Override
	public boolean assignShift(Shift shift, Employee employee) {
		if (shift.getShiftEmployees().contains(employee)) {
			System.out
					.println(MainProgram.ANSI_RED + "The employee already assigned to shift" + MainProgram.ANSI_RESET);
			return false;
		} else {
			shift.assignEmployee(employee);
			System.out.println(MainProgram.ANSI_GREEN + "The employee assigned successfully!" + MainProgram.ANSI_RESET);
			return true;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T searchShift() {
		LocalDate date;
		LocalTime time;
		Shift s;
		System.out.print("Enter shift date (dd.MM.yy): ");
		date = InputCheck.scanToLocalDate(MainProgram.input);
		System.out.print("Enter shift time (HH:mm): ");
		time = InputCheck.scanToLocalTime(MainProgram.input);
		s = ShiftDataBase.getInstance().getShiftByDateAndTime(date, time);
		if (s != null) {
			System.out.println(s);
			return (T) s;
		}
		System.out.println(MainProgram.ANSI_RED + "Shift not found" + MainProgram.ANSI_RESET);
		return null;
	}

	@Override
	public void addShift() {
		LocalDate startDate, endDate;
		LocalTime startTime, endTime;
		LocalDateTime start, end;
		System.out.print("Enter shift starting date (dd.MM.yy): ");
		startDate = InputCheck.scanToLocalDate(MainProgram.input);
		while (startDate.isBefore(LocalDate.now())) {
			System.out.println("This date already passed, Enter date again");
			startDate = InputCheck.scanToLocalDate(MainProgram.input);
		}
		System.out.print("Enter shift start time (HH:mm): ");
		startTime = InputCheck.scanToLocalTime(MainProgram.input);
		while (startDate.equals(LocalDate.now()) && startTime.isBefore(LocalTime.now())) {
			System.out.println("This time already passed, Enter start time again");
			startTime = InputCheck.scanToLocalTime(MainProgram.input);
		}
		start = LocalDateTime.of(startDate, startTime);

		System.out.print("Enter shift end date (dd.MM.yy): ");
		endDate = InputCheck.scanToLocalDate(MainProgram.input);
		while (endDate.isBefore(startDate)) {
			System.out.println("End date can't be before start date, Enter end date again");
			endDate = InputCheck.scanToLocalDate(MainProgram.input);
		}
		System.out.print("Enter shift end time (HH:mm): ");
		endTime = InputCheck.scanToLocalTime(MainProgram.input);
		end = LocalDateTime.of(endDate, endTime);

		while (end.isBefore(start)) {
			System.out.println("End time can't be before start time, Enter end time again");
			endTime = InputCheck.scanToLocalTime(MainProgram.input);
			end = LocalDateTime.of(endDate, endTime);
		}
		Random ran = new Random();
		Shift s = new Shift(ran.nextInt(90000) + 10000, start, end);
		ShiftDataBase.getInstance().addShift(s);
		System.out.println(MainProgram.ANSI_GREEN + s + " Added successfully" + MainProgram.ANSI_RESET);
	}

	@Override
	public void updateShift(Shift shift) {
		LocalDate sdate = shift.getStart().toLocalDate();
		LocalDate edate = shift.getEnd().toLocalDate();
		System.out.println("What would you like to update?");
		System.out.println("1.Starting time 2.Ending time");
		char choice;
		boolean finish = false;
		while (!finish) {
			choice = MainProgram.input.nextLine().charAt(0);
			switch (choice) {
			case '1':
				System.out.print("Enter new starting time:");
				LocalTime stime = InputCheck.scanToLocalTime(MainProgram.input);
				shift.setStart(LocalDateTime.of(sdate, stime));
				;
				finish = true;
				break;
			case '2':
				System.out.print("Enter new ending time:");
				LocalTime etime = InputCheck.scanToLocalTime(MainProgram.input);
				shift.setEnd(LocalDateTime.of(edate, etime));
				finish = true;
				break;
			default:
				System.out.print(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
			}
		}

	}

	@Override
	public void deleteShift(Shift shift) {
		ShiftDataBase.getInstance().deleteShift(shift);

	}

	@Override
	public void printShifts(char printBy, EmployeeRoles role) {
		char managerChoice = '0';
		Employee employee = null;
		LocalDate date;
		String ID;
		List<Shift> shifts = null;
		System.out.println("Enter starting date (dd.MM.yy): ");
		date = InputCheck.scanToLocalDate(MainProgram.input);
		if (role.equals(EmployeeRoles.MANAGER) || role.equals(EmployeeRoles.SHIFT_MANAGER)) {
			System.out.println("1.Print employee shifts 2.Print all shifts");
			managerChoice = MainProgram.input.nextLine().charAt(0);
			while (managerChoice != '1' && managerChoice != '2') {
				System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
				managerChoice = MainProgram.input.nextLine().charAt(0);
			}
			if (managerChoice == '1') {
				System.out.print("Enter employee ID: ");
				ID = InputCheck.checkID(MainProgram.input , true);
				while (EmployeeDatabase.getInstance().getEmployeeByID(ID) == null) {
					System.out.println(MainProgram.ANSI_RED + "Emloyee not found, Try again" + MainProgram.ANSI_RESET);
					ID = InputCheck.checkID(MainProgram.input , true);
				}
				employee = EmployeeDatabase.getInstance().getEmployeeByID(ID);
			}
		}
		switch (printBy) {
		case '1':
			shifts = ShiftDataBase.getInstance().getShiftsByDay(date);
			break;
		case '2':
			shifts = ShiftDataBase.getInstance().getShiftsByWeek(date);
			break;
		case '3':
			shifts = ShiftDataBase.getInstance().getShiftsByMonth(date);
			break;
		default:
			System.out.println(MainProgram.ANSI_RED + "Wrong input, Try again" + MainProgram.ANSI_RESET);
		}
		if (shifts.isEmpty())
			System.out.println(MainProgram.ANSI_RED + "No shifts found" + MainProgram.ANSI_RESET);
		else {
			if (role.equals(EmployeeRoles.EMPLOYEE)) {
				shifts = ShiftDataBase.getInstance().getShiftsByEmployee(shifts, MainProgram.employee);
				if (shifts.isEmpty())
					System.out.println(MainProgram.ANSI_RED + "No shifts found" + MainProgram.ANSI_RESET);
				else {
					System.out.println(MainProgram.ANSI_BLUE + "Your shifts are:" + MainProgram.ANSI_RESET);
					for (Shift s : shifts)
						System.out.println(s);
				}
			} else {
				if (managerChoice == '1') {
					shifts = ShiftDataBase.getInstance().getShiftsByEmployee(shifts, employee);
					if (shifts.isEmpty())
						System.out.println(MainProgram.ANSI_RED + "No shifts found" + MainProgram.ANSI_RESET);
					else {
						System.out.println(MainProgram.ANSI_BLUE + employee.getFirstName() + " shifts are:"
								+ MainProgram.ANSI_RESET);
						for (Shift s : shifts)
							System.out.println(s);
					}
				} else if (managerChoice == '2') {
					System.out.println(MainProgram.ANSI_BLUE + "All shifts are:" + MainProgram.ANSI_RESET);
					for (Shift s : shifts) {
						System.out.println(MainProgram.ANSI_BOLD + s + MainProgram.ANSI_RESET);
						if (!s.getShiftEmployees().isEmpty()) {
							System.out.println(MainProgram.ANSI_CYAN + "Employees assigned to this shift are:"
									+ MainProgram.ANSI_RESET);
							for (Employee e : s.getShiftEmployees())
								System.out.println(e);
						} else
							System.out.println(MainProgram.ANSI_RED + "No employees assigned to this shift"
									+ MainProgram.ANSI_RESET);
					}
				}
			}
		}
	}

}
