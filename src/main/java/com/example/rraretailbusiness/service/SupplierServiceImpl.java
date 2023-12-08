package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.SupplierDao;
import com.example.rraretailbusiness.domain.Supplier;

import java.util.List;

public class SupplierServiceImpl implements SupplierService{

    SupplierDao dao = new SupplierDao();

    @Override
    public String saveSupplier(Supplier supplier) {
        return dao.saveSupplier(supplier);
    }

    @Override
    public List<Supplier> displayAllSuppliers() {
        return dao.displayAllSuppliers();
    }

    @Override
    public boolean findSupplierId(Long Id) {
        return dao.findSupplierId(Id);
    }

    @Override
    public boolean deleteSupplier(Long Id) {
        return dao.deleteSupplier(Id);
    }

    @Override
    public boolean UpdateSupplier(Supplier supplier, Long Id) {
        return dao.UpdateSupplier(supplier, Id);
    }
}
