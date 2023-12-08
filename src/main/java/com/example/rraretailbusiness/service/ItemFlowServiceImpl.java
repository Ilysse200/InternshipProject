package com.example.rraretailbusiness.service;


import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.domain.ItemFlow;

import java.util.List;

public class ItemFlowServiceImpl implements ItemFlowService{

   ItemFlowDao dao = new ItemFlowDao();


    @Override
    public String saveItemFlow(ItemFlow itemFlow) {
        return dao.saveItemFlow(itemFlow);
    }

    @Override
    public List<ItemFlowDao> displayAllItemFlows() {
        return dao.displayAllEmployees();
    }

    @Override
    public boolean findItemFlowId(Long Id) {
        return dao.findItemFlowId(Id);
    }

    @Override
    public boolean deleteItemFlow(Long Id) {
        return dao.deleteItemFlow(Id);
    }

    @Override
    public boolean UpdateItemFlow(ItemFlow itemFlow, Long Id) {
        return dao.UpdateItemFlow(itemFlow, Id);
    }
}
