package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Supplier;

import java.util.List;

public interface SupplierService {

    String saveSupplier(Supplier supplier) ;
    List<Supplier> displayAllSuppliers();
    Supplier findSupplierId(Long Id);
    boolean deleteSupplier(Long Id);
    boolean UpdateSupplier(Supplier supplier, Long Id);
}
