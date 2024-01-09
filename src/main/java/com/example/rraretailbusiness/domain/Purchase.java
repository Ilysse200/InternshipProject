/**
 * The following entity create the purchase entity in the database
 * The purchases table stores information of the purchases carried out by an employee
 */


package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseCode")
    private Long purchaseId;

    @Column(name = "dateOfPurchase")
    private LocalDate purchaseDate;


    @ManyToOne
    @JoinColumn(name = "supplierIdentifier")
    private Supplier supplierId;


    @OneToOne
    @JoinColumn(name = "employeeIdentifier")
    private Employee empPurchase;

    //This is an empty constructor
    public Purchase() {
    }

    //parameterized constructor without the primary Key


    // Constructor to save the purchase without supplier


    public Purchase(LocalDate purchaseDate, Employee empPurchase) {
        this.purchaseDate = purchaseDate;
        this.empPurchase = empPurchase;
    }

    public Purchase(LocalDate purchaseDate, Supplier supplierId,Employee empPurchase) {
        this.purchaseDate = purchaseDate;
        this.supplierId = supplierId;
        this.empPurchase = empPurchase;
    }

    //Parameterised contructor that facilitates the construction of new objects

    public Purchase(Long purchaseId, LocalDate purchaseDate, Supplier purchaseSupplier, Employee empPurchase) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.supplierId = purchaseSupplier;
        this.empPurchase = empPurchase;
    }


    /*
       The following are getters and setters, to enable us to make changes on the variable of a class
       All variables can be changed except the primary key. This is why no setter for the purchaseId is available
     */

    public Long getPurchaseId() {
        return purchaseId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }


    public Employee getEmpPurchase() {
        return empPurchase;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }



    public void setEmpPurchase(Employee empPurchase) {
        this.empPurchase = empPurchase;
    }
}
