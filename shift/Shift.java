package shift;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import employee.Employee;

public class Shift {

	private int code;
	private LocalDateTime start;
	private LocalDateTime end;
	private Set<Employee> employees;

	public Shift(int code, LocalDateTime start, LocalDateTime end) {
		this.code = code;
		this.start = start;
		this.end = end;
		this.employees = new HashSet<Employee>();
	}

	public int getCode() {
		return code;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public void assignEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public Set<Employee> getShiftEmployees() {
		return employees;
	}

	@Override
	public String toString() {
		return "Shift: code: " + code + ", start time: " + start.toLocalDate() + ", " + start.toLocalTime()
				+ ", end time: " + end.toLocalDate() + ", " + end.toLocalTime();
	}
}
