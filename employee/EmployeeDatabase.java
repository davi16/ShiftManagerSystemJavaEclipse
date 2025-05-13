package employee;

//Students submitting: Kristina goldin 317958700, David Ben Yaacov 320759921
import java.util.HashSet;
import java.util.Set;

public class EmployeeDatabase {

	private Set<Employee> employees;
	private static EmployeeDatabase emploDataBase;

	private EmployeeDatabase() {
		this.employees = new HashSet<Employee>();
	}

	public synchronized static EmployeeDatabase getInstance() {
		if (emploDataBase == null)
			emploDataBase = new EmployeeDatabase();
		return emploDataBase;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public boolean isEmployee(Employee employee) {
		return employees.contains(employee);
	}

	public Employee getEmployeeByUser(String user) {
		for (Employee employee : employees) {
			if (employee.getUsername().compareTo(user) == 0)
				return employee;
		}
		return null;
	}

	public Employee getEmployeeByID(String id) {
		for (Employee employee : employees) {
			if (employee.getID().compareTo(id) == 0)
				return employee;
		}
		return null;
	}

}
