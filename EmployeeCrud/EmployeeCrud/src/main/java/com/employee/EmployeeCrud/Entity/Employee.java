package com.employee.EmployeeCrud.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Employees")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "employeeNumber")
    private String employeeNum;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "salary")
    private double salary;


    @Column(name = "role")
    private String role;

    @Column(name = "manager")
    private String manager;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNumber(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
