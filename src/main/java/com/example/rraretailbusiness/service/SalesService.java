package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Sales;

import java.util.List;

public interface SalesService {

    String saveSales(Sales sales) ;
    List<Sales> displayAllSales();
    boolean findSalesId(Long Id);
    boolean deleteSales(Long Id);
    boolean UpdateSales(Sales sales, Long Id);
}
