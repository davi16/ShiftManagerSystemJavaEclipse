package interfaces;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;

public interface CheckInOutManagement {

	public void checkIn(Employee employee);

	public void checkOut(Employee employee);

	public void printRecord();
}
