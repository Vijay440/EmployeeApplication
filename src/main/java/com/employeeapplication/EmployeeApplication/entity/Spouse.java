package com.employeeapplication.EmployeeApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spouse")
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mobileNo;
    private int age;

    @OneToOne(mappedBy = "spouse")
    private Employee employee;

    public Spouse() {
    }

    public Spouse(String name, String mobileNo, int age) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.age = age;
    }

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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
