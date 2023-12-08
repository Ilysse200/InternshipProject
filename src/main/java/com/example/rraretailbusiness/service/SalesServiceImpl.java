package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.SalesDao;
import com.example.rraretailbusiness.domain.Sales;

import java.util.List;

public class SalesServiceImpl implements SalesService{

    SalesDao dao = new SalesDao();

    @Override
    public String saveSales(Sales sales) {
        return dao.saveSales(sales);
    }

    @Override
    public List<Sales> displayAllSales() {
        return dao.displayAllSales();
    }

    @Override
    public boolean findSalesId(Long Id) {
        return dao.findSalesId(Id);
    }

    @Override
    public boolean deleteSales(Long Id) {
        return dao.deleteSales(Id);
    }

    @Override
    public boolean UpdateSales(Sales sales, Long Id) {
        return dao.UpdateSales(sales, Id);
    }
}
