package interfaces;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;
import employee.EmployeeRoles;
import shift.Shift;

public interface ShiftManagment {

	public boolean assignShift(Shift shift, Employee employee);

	public <T> T searchShift();

	public void addShift();

	public void updateShift(Shift shift);

	public void deleteShift(Shift shift);

	public void printShifts(char printBy, EmployeeRoles role);
}
