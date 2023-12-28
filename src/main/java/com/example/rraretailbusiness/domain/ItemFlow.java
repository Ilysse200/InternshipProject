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

    @OneToOne
    @JoinColumn(name = "purchasesId",referencedColumnName = "purchaseCode")
    private Purchase purchasesItemFlow;
    @OneToOne
    @JoinColumn(name = "sales", referencedColumnName = "salesIdentifier")
    private Sales ItemFlowsalesID;


    @OneToOne
    @JoinColumn(name = "listOfItems")
    private Item itemList;


    //This is an empty constructor
    public ItemFlow() {
    }


    //Parameterized constructor that facilitate the creation of objects
    public ItemFlow(Long itemFlowId, LocalDate itemFlowDate, Purchase purchasesItemFlow, Sales itemFlowsalesID, Item itemList) {
        this.itemFlowId = itemFlowId;
        this.itemFlowDate = itemFlowDate;
        this.purchasesItemFlow = purchasesItemFlow;
        this.ItemFlowsalesID = itemFlowsalesID;
        this.itemList = itemList;
    }

    public ItemFlow(LocalDate itemFlowDate, Purchase purchasesItemFlow, Sales itemFlowsalesID, Item itemList) {
        this.itemFlowDate = itemFlowDate;
        this.purchasesItemFlow = purchasesItemFlow;
        ItemFlowsalesID = itemFlowsalesID;
        this.itemList = itemList;
    }

    public ItemFlow(LocalDate itemFlowDate, Purchase purchasesItemFlow, Item itemList) {
        this.itemFlowDate = itemFlowDate;
        this.purchasesItemFlow = purchasesItemFlow;
        this.itemList = itemList;
    }

    public ItemFlow(LocalDate itemFlowDate, Sales itemFlowsalesID, Item itemList) {
        this.itemFlowDate = itemFlowDate;
        ItemFlowsalesID = itemFlowsalesID;
        this.itemList = itemList;
    }
    /*
         The following below are getters and setters, they help us modify the variables of a class.
         Every column or field of the class ItemFlow has got setters except the itemFlowId, so that
         none can change it.
     */

    public Long getItemFlowId() {
        return itemFlowId;
    }

    public LocalDate getItemFlowDate() {
        return itemFlowDate;
    }

    public Purchase getPurchasesItemFlow() {
        return purchasesItemFlow;
    }

    public Sales getItemFlowsalesID() {
        return ItemFlowsalesID;
    }

    public Item getItemList() {
        return itemList;
    }


    public void setItemFlowDate(LocalDate itemFlowDate) {
        this.itemFlowDate = itemFlowDate;
    }

    public void setPurchasesItemFlow(Purchase purchasesItemFlow) {
        this.purchasesItemFlow = purchasesItemFlow;
    }

    public void setItemFlowsalesID(Sales itemFlowsalesID) {
        ItemFlowsalesID = itemFlowsalesID;
    }

    public void setItemList(Item itemList) {
        this.itemList = itemList;
    }
}
