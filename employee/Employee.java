package employee;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import shiftManager.CheckInOutRecord;
import shiftManager.MainProgram;
import shiftNotifier.AssignShiftNotifier;
import shiftNotifier.Observable;
import shiftNotifier.Observer;
import shiftNotifier.UpdateShiftNotifier;

public class Employee implements Observer {

	private String ID, firstName, lastName, username;
	private LocalDate birthDate;
	private EmployeeRoles role;
	private int rank;
	private Set<CheckInOutRecord> records;

	public Employee(String ID, String firstName, String lastName, LocalDate birthDate, EmployeeRoles role, int rank) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.role = role;
		this.rank = rank;
		records = new HashSet<CheckInOutRecord>();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public EmployeeRoles getRole() {
		return role;
	}

	public void setRole(EmployeeRoles role) {
		this.role = role;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addRecord(CheckInOutRecord record) {
		records.add(record);
	}

	public Set<CheckInOutRecord> getRecords() {
		return records;
	}

	@Override
	public String toString() {
		return "Employee: ID:" + ID + ", First Name:" + firstName + ", Last Name:" + lastName + ", BirthDate:"
				+ birthDate + ", Role:" + role + ", Rank:" + rank;
	}

	@Override
	public void notify(Observable obs) {
		if (obs instanceof AssignShiftNotifier)
			System.out.println(
					MainProgram.ANSI_BOLD + "Hello, " + this.firstName + "\nYou have been assingned to the shift:\n"
							+ ((AssignShiftNotifier) obs).getShift() + MainProgram.ANSI_RESET);

		if (obs instanceof UpdateShiftNotifier)
			System.out.println(MainProgram.ANSI_BOLD + "Hello, " + this.firstName
					+ "\nThe shift that you are assigned to has changed:\n" + ((UpdateShiftNotifier) obs).getShift()
					+ MainProgram.ANSI_RESET);

	}
}
