package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.domain.ItemFlow;

import java.util.List;

public interface ItemFlowService {
    String saveItemFlow(ItemFlow itemFlow) ;
    List<ItemFlowDao> displayAllItemFlows();
    boolean findItemFlowId(Long Id);
    boolean deleteItemFlow(Long Id);
    boolean UpdateItemFlow(ItemFlow itemFlow, Long Id);
}
