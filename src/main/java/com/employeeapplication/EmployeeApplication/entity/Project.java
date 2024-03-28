package com.employeeapplication.EmployeeApplication.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String clientName;

    public Project() {
    }

    public Project(String name, String clientName) {
        this.name = name;
        this.clientName = clientName;
    }

    public Project(String name, String clientName, Employee employee) {
        this.name = name;
        this.clientName = clientName;
        this.employee =new ArrayList<>();
    }

    @ManyToMany(mappedBy = "project")
    private List<Employee> employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
