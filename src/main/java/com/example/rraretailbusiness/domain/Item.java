/**
 * The following class represent creates an Item entity in the db
 * The item table will store information of the employee
 */


package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "item")

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemIdentifier")
    private Long itemCode;

    private String itemName;

    private String itemOrigin;

    @Column(name = "package")
    private String itemMeasure;

    @Column(name = "itemUnitPrice")
    private Long itemUnit;

    private String itemTaxType;
    private LocalDate registeredDate;


    //This is an empty constructor
    public Item() {
    }


    //Parametirized constructor to enable the creation of an object
    public Item(Long itemCode, String itemName, String itemOrigin, String itemMeasure, Long itemUnit, String itemTaxType, LocalDate registeredDate) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemOrigin = itemOrigin;
        this.itemMeasure = itemMeasure;
        this.itemUnit = itemUnit;
        this.itemTaxType = itemTaxType;
        this.registeredDate = registeredDate;
    }


    /*The getters and setters to make changes on the variables of the class,
    /The Item id does not have a setter to make sure that no one can change the Id/primary key once it's set*/
    public Long getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemOrigin() {
        return itemOrigin;
    }

    public String getItemMeasure() {
        return itemMeasure;
    }

    public Long getItemUnit() {
        return itemUnit;
    }

    public String getItemTaxType() {
        return itemTaxType;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemOrigin(String itemOrigin) {
        this.itemOrigin = itemOrigin;
    }

    public void setItemMeasure(String itemMeasure) {
        this.itemMeasure = itemMeasure;
    }

    public void setItemUnit(Long itemUnit) {
        this.itemUnit = itemUnit;
    }

    public void setItemTaxType(String itemTaxType) {
        this.itemTaxType = itemTaxType;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
