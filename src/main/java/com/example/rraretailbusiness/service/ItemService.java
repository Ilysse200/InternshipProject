package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Item;

import java.util.List;

public interface ItemService {

    String saveItem(Item item) ;
    List<Item> displayAllItems();
    Item findItemId(Long Id);
    boolean deleteItem(Long Id);
    boolean UpdateItem(Item item, Long Id);
}
