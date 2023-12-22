package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "salesTable")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesIdentifier")
    private Long salesID;

    private LocalDate salesDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerTrack")
    private Customer customerId;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ItemCode")
    private List<Item> items;

    @OneToMany
            (
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    @JoinColumn(name = "doneBy")
    private List<Employee> salesExecuter;


    //This is an empty constructor
    public Sales() {
    }


    //The following are parameterised constructors they enable us to create objects
    public Sales(Long salesID, LocalDate salesDate, Customer customerId, List<Item> items, List salesExecuter) {
        this.salesID = salesID;
        this.salesDate = salesDate;
        this.customerId = customerId;
        this.items = items;
        this.salesExecuter = salesExecuter;
    }

    /*
        The following are getters and setters, they enable us to make changes in the fields of a class.
        All fields have associated getters and setters except the salesId which is to prevent any changes to be
        done to salesId(which is a primary key) while updating.
     */

    public Long getSalesID() {
        return salesID;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public Customer getCustomer() {
        return customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public List getSalesExecuter() {
        return salesExecuter;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public void setCustomer(Customer customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setSalesExecuter(List salesExecuter) {
        this.salesExecuter = salesExecuter;
    }
}
