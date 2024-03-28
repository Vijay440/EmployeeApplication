package com.employeeapplication.EmployeeApplication.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.data.util.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "employeeId")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int employeeId;
    String employeeName;
    String employeeCity;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch =FetchType.LAZY)
    @JoinColumn(name = "fk_spouse")
    private Spouse spouse;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Address> addresses;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_project",joinColumns = @JoinColumn(name="fk_employee"),inverseJoinColumns = @JoinColumn(name= "fk_project"))
    private Set<Project> project;

    public Employee(String employeeName, String employeeCity) {
        this.employeeName = employeeName;
        this.employeeCity = employeeCity;
    }
    public Employee(int employeeId, String employeeName, String employeeCity) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeCity = employeeCity;
    }
    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }

    public Spouse getSpouse() {
        return spouse;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }

    public void addAddress(Address address)
    {

        this.addresses =new ArrayList<>();
        this.addresses.add(address);
        address.setEmployee(this);
    }
    public void removeAddress(Address address)
    {
        this.addresses.remove(address);
        address.setEmployee(null);
    }
    public void addProject(Project project)
    {
        this.project =new HashSet<>();
        this.project.add(project);
        System.out.println(project.getEmployee());
        project.getEmployee().add(this);
    }

    public void removeProject(Project project)
    {
        this.project.remove(project);
        project.getEmployee().remove(project);
    }

}
