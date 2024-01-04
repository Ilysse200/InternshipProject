package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.ItemDao;
import com.example.rraretailbusiness.domain.Item;

import java.util.List;

public class ItemServiceImpl implements ItemService{

    ItemDao dao = new ItemDao();
    @Override
    public String saveItem(Item item) {
        return dao.saveItem(item);
    }

    @Override
    public List<Item> displayAllItems() {
        return dao.displayAllEmployees();
    }

    @Override
    public Item findItemId(Long Id) {
        return dao.findItemId(Id);
    }

    @Override
    public boolean deleteItem(Long Id) {
        return dao.deleteItem(Id);
    }

    @Override
    public boolean UpdateItem(Item item, Long Id) {
        return dao.UpdateItem(item, Id);
    }
}
