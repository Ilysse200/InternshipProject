/**
 * The following class represent creates a customer entity in the db
 * The customer table will store information of the employee
 */
package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerIdentifier")
    private Long customerId;
    private String customerName;
    private String customerTel;
    private String customerMail;
    private String customerAddress;
    @Column(name = "customerTin", unique = true)
    private String customerTin;

    @OneToMany(mappedBy = "customerId")
    private List<Sales> sales;


    //This is an empty constructor
    public Customer() {
    }


    //Parametirized constructor to enable the creation of an object
    public Customer(Long customerId, String customerName, String customerTel, String customerMail, String customerAddress, String customerTin) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerTel = customerTel;
        this.customerMail = customerMail;
        this.customerAddress = customerAddress;
        this.customerTin = customerTin;
    }


    //Constructor with all field except of the id
    public Customer(String customerName, String customerTel, String customerMail, String customerAddress, String customerTin) {
        this.customerName = customerName;
        this.customerTel = customerTel;
        this.customerMail = customerMail;
        this.customerAddress = customerAddress;
        this.customerTin = customerTin;
    }

    /*The getters and setters to make changes on the variables of the class,
        /The customer id does not have a setter to make sure that no one can change the Id/primary key once it's set*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerTin(String customerTin) {
        this.customerTin = customerTin;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerTin() {
        return customerTin;
    }
}
