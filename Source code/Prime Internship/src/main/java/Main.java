import java.sql.Date;

import Model.*;
import Services.*;
import Implementation.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeServiceImplementation newEmployee = new EmployeeServiceImplementation();
		Date dateOfBirth = new Date(12,03,2001);
		newEmployee.CreateNewEmployee(7, "Mirko", "Slavic", "miree@gamil.com", "0654328345", dateOfBirth, 120000);
		
	}

}
