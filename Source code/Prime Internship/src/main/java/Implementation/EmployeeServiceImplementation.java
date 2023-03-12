package Implementation;
import Model.Task;
import Services.TaskService;
import Implementation.TaskServiceImplementation;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Model.Employee;
import Services.EmployeeService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Remote(EmployeeService.class)
@Stateless
public class EmployeeServiceImplementation implements EmployeeService {
	
	private EntityManager em;
	TaskServiceImplementation taskService;
	public EmployeeServiceImplementation()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimeIntern");
		em=emf.createEntityManager();
	}

	public void CreateNewEmployee(int idEmployee, String firstName, String lastName, String email, String phoneNumber,
			Date dateOfBirth, int monthlySalary) {
		Employee newEmployee = new Employee(idEmployee, firstName, lastName, email, phoneNumber, dateOfBirth, monthlySalary);
		em.getTransaction().begin();
		em.persist(newEmployee);
		em.getTransaction().commit();
		System.out.println("Creating new employee succeded!");
	}

	public Employee ReadEmployee(int idEmployee) {
		Employee readEmployee = em.find(Employee.class, idEmployee);
		return readEmployee;

	}

	public void UpdateEmployee(int idEmployee, String firstName, String lastName, String email, String phoneNumber,
			Date dateOfBirth, int monthlySalary) {
		Employee readEmployee= ReadEmployee(idEmployee);
		if(readEmployee != null) {
		em.getTransaction().begin();
		readEmployee.setFirstName(firstName);
		readEmployee.setLastName(lastName);
		readEmployee.setEmail(email);
		readEmployee.setPhoneNumber(phoneNumber);
		readEmployee.setDateOfBirth(dateOfBirth);
		readEmployee.setMonthlySalary(monthlySalary);
		em.getTransaction().commit();	
		}else {
			System.out.println("The employee does not exist! Updating is not possible!");
		}

	}

	public void DeleteEmployee(int idEmployee) {
		Employee readEmployee= ReadEmployee(idEmployee);
		if(readEmployee != null) {
			em.getTransaction().begin();
			em.remove(readEmployee);
			em.getTransaction().commit();
		}else {
			System.out.println("The employee does not exist! Delete is not possible!");
		}
		

	}

	public List<Employee> ReturnFiveEmployeesWithLargestNumberOfTasksLastMonth() {
		List<Task> allFinishedInLastMonth = taskService.FindFinishedTasksInPastMonth();
		
		List<Employee> employeeTaskCounts = new ArrayList<Employee>();
	    for (Task task : allFinishedInLastMonth) {
	        int assignee = task.getAssignee();
	        boolean found = false;
	        for (Employee employee : employeeTaskCounts) {
	            if (employee.getIdEmployee() == assignee) {
	                employee.setTaskCount(employee.getTaskCount() + 1);
	                found = true;
	                break;
	            }
	            if (!found) {
	            	employee.setTaskCount(0);
	                employeeTaskCounts.add(employee);
	            }
	        }
	        
	    }

	    employeeTaskCounts.sort(Comparator.comparing(Employee::getTaskCount).reversed());
	    int size = Math.min(5, employeeTaskCounts.size());
	    List<Employee> topEmployees = new ArrayList<Employee>(employeeTaskCounts.subList(0, size));
	    return topEmployees;
		
	}

	public List<Employee> listEmployee() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM EmEployee e", Employee.class);
		List<Employee> allEmployee = query.getResultList();
		return allEmployee;
	}

}
