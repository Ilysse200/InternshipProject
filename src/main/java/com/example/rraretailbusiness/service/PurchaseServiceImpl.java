package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.PurchaseDao;
import com.example.rraretailbusiness.domain.Purchase;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService{

    PurchaseDao dao = new PurchaseDao();
    @Override
    public String savePurchase(Purchase purchase) {
        return dao.savePurchase(purchase);
    }

    @Override
    public List<Purchase> displayAllPurchases() {
        return dao.displayAllPurchases();
    }

    @Override
    public boolean findPurchaseId(Long Id) {
        return dao.findPurchaseId(Id);
    }

    @Override
    public boolean deletePurchase(Long Id) {
        return dao.deletePurchase(Id);
    }

    @Override
    public boolean UpdatePurchase(Purchase purchase, Long Id) {
        return dao.UpdatePurchase(purchase, Id);
    }
}
