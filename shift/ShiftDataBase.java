package shift;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import employee.Employee;

public class ShiftDataBase {

	private List<Shift> shifts;
	private static ShiftDataBase shiftDataBase;

	private ShiftDataBase() {
		this.shifts = new ArrayList<Shift>();
	}

	public synchronized static ShiftDataBase getInstance() {
		if (shiftDataBase == null)
			shiftDataBase = new ShiftDataBase();
		return shiftDataBase;
	}

	public void addShift(Shift shift) {
		shifts.add(shift);
	}

	public void deleteShift(Shift shift) {
		shifts.remove(shift);
	}

	public List<Shift> getShifts() {
		return shifts;
	}

	public List<Shift> getShiftsByDay(LocalDate date) {
		List<Shift> shiftsByDay = new ArrayList<Shift>();
		for (Shift shift : shifts) {
			if (shift.getStart().toLocalDate().compareTo(date) == 0)
				shiftsByDay.add(shift);
		}
		return shiftsByDay;
	}

	public List<Shift> getShiftsByWeek(LocalDate startDate) {
		List<Shift> shiftsByWeek = new ArrayList<Shift>();
		for (Shift shift : shifts) {
			if (shift.getStart().toLocalDate().isAfter(startDate)
					&& shift.getStart().toLocalDate().isBefore(startDate.plusWeeks(1)))
				shiftsByWeek.add(shift);
		}
		sortShiftsByDate(shiftsByWeek);
		return shiftsByWeek;
	}

	public List<Shift> getShiftsByMonth(LocalDate startDate) {
		List<Shift> shiftsByMonth = new ArrayList<Shift>();
		for (Shift shift : shifts) {
			if (shift.getStart().toLocalDate().isAfter(startDate)
					&& shift.getStart().toLocalDate().isBefore(startDate.plusMonths(1)))
				shiftsByMonth.add(shift);
		}
		sortShiftsByDate(shiftsByMonth);
		return shiftsByMonth;

	}

	public List<Shift> getShiftsByEmployee(List<Shift> shifts, Employee employee) {
		List<Shift> shiftsByEmployee = new ArrayList<Shift>();
		for (Shift shift : shifts) {
			if (shift.getShiftEmployees().contains(employee))
				shiftsByEmployee.add(shift);
		}
		sortShiftsByDate(shiftsByEmployee);
		return shiftsByEmployee;
	}

	public Shift getShiftByDateAndTime(LocalDate date, LocalTime time) {
		for (Shift shift : shifts) {
			if (shift.getStart().toLocalDate().equals(date) && shift.getStart().toLocalTime().equals(time))
				return shift;
		}
		return null;
	}

	private void sortShiftsByDate(List<Shift> shifts) {

		Comparator<Shift> compareByDate = new Comparator<Shift>() {

			@Override
			public int compare(Shift s1, Shift s2) {
				int rv;
				if (s1.getStart().isAfter(s2.getStart()))
					rv = -1;
				else if (s1.getStart().isBefore(s2.getStart()))
					rv = 1;
				else
					rv = 0;
				return rv;
			}
		};

		Collections.sort(shifts, compareByDate);
	}
}
