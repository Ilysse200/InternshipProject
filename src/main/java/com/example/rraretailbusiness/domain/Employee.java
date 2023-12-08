/**
 * The following class represent creates an employee entity in the db
 * The employee table will store information of the employee
 */


package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeIdentifier")
    private Long empID;
    @Column(name = "employeeFirstName")
    private String empFirstName;
    @Column(name = "employeeLastName")
    private String empLastName;
    @Column(name = "employeeUserName", unique = true)
    private String empUserName;
    @Column(name = "employeePassword")
    private String empPassword;
    @Column(name = "employeeMail")
    private String empMail;
    @Column(name = "employeeDOB")
    private LocalDate empDOB;

    //This is an empty constructor

    public Employee() {
    }

    //Parametirized constructor to enable the creation of an object
    public Employee(Long empID, String empFirstName, String empLastName, String empUserName, String empPassword, String empMail, LocalDate empDOB) {
        this.empID = empID;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empUserName = empUserName;
        this.empPassword = empPassword;
        this.empMail = empMail;
        this.empDOB = empDOB;
    }

    //parameterized constructor without the empId

    public Employee(String empFirstName, String empLastName, String empUserName, String empPassword, String empMail, LocalDate empDOB) {
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empUserName = empUserName;
        this.empPassword = empPassword;
        this.empMail = empMail;
        this.empDOB = empDOB;
    }

    /*The getters and setters to make changes on the variables of the class,
    /The employee id does not have a setter to make sure that no one can change the Id/primary key once it's set*/

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public void setEmpUserName(String empUserName) {
        this.empUserName = empUserName;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public void setEmpDOB(LocalDate empDOB) {
        this.empDOB = empDOB;
    }

    public Long getEmpID() {
        return empID;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public String getEmpUserName() {
        return empUserName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public String getEmpMail() {
        return empMail;
    }

    public LocalDate getEmpDOB() {
        return empDOB;
    }

}
