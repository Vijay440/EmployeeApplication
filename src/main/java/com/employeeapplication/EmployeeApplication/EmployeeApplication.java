package com.employeeapplication.EmployeeApplication;

import com.employeeapplication.EmployeeApplication.entity.Address;
import com.employeeapplication.EmployeeApplication.entity.Employee;
import com.employeeapplication.EmployeeApplication.entity.Project;
import com.employeeapplication.EmployeeApplication.entity.Spouse;
import com.employeeapplication.EmployeeApplication.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
		}
	@Bean
	public CommandLineRunner initialcreate(EmployeeService employeeService){
		return(args) -> {
			Employee employee1 = new Employee("employee1","city1");
			Address address1 =new Address("line1","line2","zipcode1","city1","state1","country1");
			Project project1 =new Project("name","clientName",employee1);
			Spouse spouse1 = new Spouse("name","12345678",29);
			System.out.println("employee name" +employee1.getEmployeeName());
			employee1.addProject(project1);
			employee1.addAddress(address1);
			employee1.setSpouse(spouse1);
			employeeService.createEmployee(employee1);

			System.out.println("getting an employee");
			employeeService.getAnEmployee(1);
		};

	}
}
