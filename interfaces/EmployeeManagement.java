package interfaces;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import employee.Employee;

public interface EmployeeManagement {

	public <T> T searchEmployee();

	public void addEmployee();

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public void printEmployees();
}
