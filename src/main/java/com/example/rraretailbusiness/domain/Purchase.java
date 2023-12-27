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


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "supplierID")
    private List<Supplier>purchaseSupplier;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "itemCode")
    private  List<Item> items;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doneBy", referencedColumnName = "employeeIdentifier")
    private Employee empPurchase;

    //This is an empty constructor
    public Purchase() {
    }

    //parameterized constructor without the primary Key

    public Purchase(LocalDate purchaseDate, List<Supplier> purchaseSupplier, List<Item> items, Employee empPurchase) {
        this.purchaseDate = purchaseDate;
        this.purchaseSupplier = purchaseSupplier;
        this.items = items;
        this.empPurchase = empPurchase;
    }

    //Parameterised contructor that facilitates the construction of new objects

    public Purchase(Long purchaseId, LocalDate purchaseDate, List<Supplier> purchaseSupplier, List<Item> items, Employee empPurchase) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.purchaseSupplier = purchaseSupplier;
        this.items = items;
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

    public List getPurchaseSupplier() {
        return purchaseSupplier;
    }

    public List getItems() {
        return items;
    }

    public Employee getEmpPurchase() {
        return empPurchase;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseSupplier(List purchaseSupplier) {
        this.purchaseSupplier = purchaseSupplier;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setEmpPurchase(Employee empPurchase) {
        this.empPurchase = empPurchase;
    }
}
