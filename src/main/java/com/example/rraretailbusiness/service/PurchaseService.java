package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Purchase;

import java.util.List;

public interface PurchaseService {

    String savePurchase(Purchase purchase) ;
    List<Purchase> displayAllPurchases();
    boolean findPurchaseId(Long Id);
    boolean deletePurchase(Long Id);
    boolean UpdatePurchase(Purchase purchase, Long Id);
}
