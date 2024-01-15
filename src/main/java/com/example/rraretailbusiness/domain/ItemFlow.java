/**
 * This entity generate the ItemFlow table in the database
 * The itemFlow table stores the purchases and sales that took place
 */


package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "itemFlow")
public class ItemFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemFlowId;
    private LocalDate itemFlowDate;


    @Column(name = "status")
    private String status;



    @OneToOne
    private Item itemList;

    private  int quantity;

    private int unitPrice;

    private int totalPrice;

    private int quantityAvailable;


    //This is an empty constructor
    public ItemFlow() {
    }



    //Parameterized constructor that facilitate the creation of objects


    public ItemFlow(Long itemFlowId, LocalDate itemFlowDate, String status, Item itemList, int quantity, int unitPrice, int totalPrice, int quantityAvailable) {
        this.itemFlowId = itemFlowId;
        this.itemFlowDate = itemFlowDate;
        this.status = status;
        this.itemList = itemList;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.quantityAvailable = quantityAvailable;
    }

    public ItemFlow(LocalDate itemFlowDate, String status, Item itemList, int quantity, int unitPrice, int totalPrice, int quantityAvailable) {
        this.itemFlowDate = itemFlowDate;
        this.status = status;
        this.itemList = itemList;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.quantityAvailable = quantityAvailable;
    }

    public ItemFlow(LocalDate itemFlowDate, String status, Item itemList) {
        this.itemFlowDate = itemFlowDate;
        this.status = status;
        this.itemList = itemList;
    }

    public ItemFlow(LocalDate itemFlowDate, String status, Item itemList, int quantity, int unitPrice, int totalPrice) {
        this.itemFlowDate = itemFlowDate;
        this.status = status;
        this.itemList = itemList;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
    /*
         The following below are getters and setters, they help us modify the variables of a class.
         Every column or field of the class ItemFlow has got setters except the itemFlowId, so that
         none can change it.
     */

    public String getStatus() {
        return status;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getItemFlowId() {
        return itemFlowId;
    }

    public LocalDate getItemFlowDate() {
        return itemFlowDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Item getItemList() {
        return itemList;
    }


    public void setItemFlowDate(LocalDate itemFlowDate) {
        this.itemFlowDate = itemFlowDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItemList(Item itemList) {
        this.itemList = itemList;
    }
}
