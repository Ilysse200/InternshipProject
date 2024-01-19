package com.example.rraretailbusiness.domain;

public class ItemData {
    private String itemName;
    private int quantity;
    private int unitPrice;
    private  int totalPrice;
    private  String status;

    // Constructor
    public ItemData(String itemName, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


    public ItemData(String itemName, int quantity, int unitPrice, int totalPrice, String status) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public ItemData(String itemName, int quantity, int unitPrice, int totalPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ItemData(int quantity, int unitPrice, String itemName) {
    }

    // Getters and setters (optional)
    public String getItemName() {
        return itemName;
    }




    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // You may want to override toString for better debugging or printing
    @Override
    public String toString() {
        return "ItemData{" +
                "itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
