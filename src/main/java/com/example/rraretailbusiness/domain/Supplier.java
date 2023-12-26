/**
 * The following is the table of supplier
 * It stores the information of the supplier
  */



package com.example.rraretailbusiness.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierIdentifier")
    private Long supplierId;
    @Column(name = "supplierNames", nullable = false)
    private String supplierName;
    @Column(name = "supplierMobile")
    private String supplierTel;
    @Column(name = "supplierMail")
    private String supplierEmail;

    @Column(name = "supplierLocation")
    private  String supplierAddress;
    @Column(name = "supplierTin", nullable = false)
    private  String supplierTin;


    //This is an empty constructor

    public Supplier() {
    }

    //Parametirized constructor to enable the creation of an object
    public Supplier(Long supplierId, String supplierName, String supplierTel, String supplierEmail, String supplierAddress, String supplierTin) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierTel = supplierTel;
        this.supplierEmail = supplierEmail;
        this.supplierAddress = supplierAddress;
        this.supplierTin = supplierTin;
    }
    
    //Parameterized constructor without the primary key of the supplier

    public Supplier(String supplierName, String supplierTel, String supplierEmail, String supplierAddress, String supplierTin) {
        this.supplierName = supplierName;
        this.supplierTel = supplierTel;
        this.supplierEmail = supplierEmail;
        this.supplierAddress = supplierAddress;
        this.supplierTin = supplierTin;
    }

    /*The getters and setters to make changes on the variables of the class,
        /The supplier id does not have a setter to make sure that no one can change the Id/primary key once it's set*/
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public void setSupplierTin(String supplierTin) {
        this.supplierTin = supplierTin;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierTel() {
        return supplierTel;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public String getSupplierTin() {
        return supplierTin;
    }
}
