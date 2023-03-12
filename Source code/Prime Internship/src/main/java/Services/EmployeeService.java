package Services;
import Model.Employee;

import java.sql.Date;
import java.util.List;

public interface EmployeeService {
	public void CreateNewEmployee(int idEmployee, String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, int monthlySalary);
	public Employee ReadEmployee(int idEmployee);
	public void UpdateEmployee(int idEmployee, String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, int monthlySalary);
	public void DeleteEmployee(int idEmployee);
	public List<Employee> ReturnFiveEmployeesWithLargestNumberOfTasksLastMonth();
	public List<Employee> listEmployee();
	
}
