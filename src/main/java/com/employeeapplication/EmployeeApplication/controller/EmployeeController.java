package com.employeeapplication.EmployeeApplication.controller;

import com.employeeapplication.EmployeeApplication.entity.Employee;
import com.employeeapplication.EmployeeApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
//@RestController
/*
It is a controller to handler the request for all the employee operation
 */
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/Employees")
    public List<Employee> findAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/Employees/{id}")
    public Employee findAnEmployee(@PathVariable int id){
        System.out.println("The employee id " + id);
        return employeeService.getAnEmployee(id);
    }
    @RequestMapping(value = "/Employees", method = RequestMethod.POST)
    public String createEmployee(@RequestBody Employee employee){
        System.out.println("The employee object " + employee);
        employeeService.createEmployee(employee);
        return "New Employee is created Successfully";
    }

    @RequestMapping(value = "/Employees/{id}", method = RequestMethod.PUT)
    public String updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return "Employee is updated Successfully";
    }

    @RequestMapping(value = "/Employees/{id}", method = RequestMethod.DELETE)
    public List deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return employeeService.getAllEmployees();
    }
}
