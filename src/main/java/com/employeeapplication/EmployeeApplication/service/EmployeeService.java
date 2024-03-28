package com.employeeapplication.EmployeeApplication.service;

import com.employeeapplication.EmployeeApplication.EmployeeRepository.EmployeeRepository;
import com.employeeapplication.EmployeeApplication.entity.Address;
import com.employeeapplication.EmployeeApplication.entity.Employee;
import com.employeeapplication.EmployeeApplication.entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class EmployeeService {

    List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(1, "FirstEmployee", "Chennai"),
            new Employee(2, "SecondEmployee", "Delhi")
    ));

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {

        //return  employeeList;
        return employeeRepository.findAll();
    }

    public Employee getAnEmployee(int id) {
//        for(Employee e: employeeList)
//        {
//            if(e.getEmployeeId() == id)
//            {
//                System.out.println("employee is retrived properly");
//                return e;
//            }
//        }
//        return null;
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        Set<Project> projectSet = new HashSet<>();
        for(Project project : employee.getProject()){
            System.out.println("lazy fetching" +project.getClientName());
        }
        return employee;
    }
    public String createEmployee(Employee employee) {
        //employeeList.add(employee);
        List<Address> addressList = new ArrayList<>();
        for(Address address : employee.getAddresses()){
            addressList.add(new Address(address.getCity(),address.getLine1(),address.getLine2(),
                    address.getCity(),
                    address.getState(),
                    address.getCountry(),
                    employee
            )
            );
        }
        Set<Project> projectSet = new HashSet<>();
        for(Project project : employee.getProject()){
            projectSet.add(new Project(project.getName(),project.getClientName(),
                    employee
                    )
            );
        }
        employee.setAddresses(addressList);
        employee.setProject(projectSet);
        employeeRepository.save(employee);
        return "New Employee is created successfully";
    }
    public String updateEmployee(Employee employee){
        System.out.println("before adding" + employee);
//        for(Employee emp: employeeList)
//        {
//            if(employee.getEmployeeId() == emp.getEmployeeId())
//            {
//                emp.setEmployeeName(employee.getEmployeeName());
//                emp.setEmployeeCity(employee.getEmployeeCity());
//            }
//        }
        employeeRepository.save(employee);
        return "Employee is updated successfully";
    }

    public void deleteEmployee(int id){
        System.out.println("Employee id going to delete" + id);
//        List<Employee> tempEmployee = new ArrayList<>();
//        for(Employee emp: employeeList)
//        {
//            if(emp.getEmployeeId() == id)
//                continue;
//            tempEmployee.add(emp);
//        }
//        this.employeeList = tempEmployee;
        employeeRepository.delete(employeeRepository.getById(id));
        System.out.println("Employee is deleted successfully");
    }
}
