package com.example.rraretailbusiness.report;

import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.domain.Item;
import com.example.rraretailbusiness.domain.ItemFlow;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceReport {
    public static void  main(String[]args){
        try {
            String filePath = "C:\\Users\\25078\\Desktop\\IMPORTANT\\WORK\\RRA\\" +
                    "RRARetailBusiness\\src\\main\\resources";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("studentName", "Lola");
            ItemFlowDao itemFlowDao = new ItemFlowDao();
            List<ItemFlow> purchases = itemFlowDao.displayAllEmployees();

            for(ItemFlow itemFlow: purchases){
                if(itemFlow!=null && itemFlow.getStatus().equals("IN")){
                    System.out.println("purchase date" + itemFlow.getItemFlowDate() + "" + " quantity " + itemFlow.getQuantity() + " "+ "unitPrice " + itemFlow.getUnitPrice() + " "+ "totalPrice " + itemFlow.getTotalPrice()  );
                }
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(purchases);

        }catch (Exception e){
            System.out.println("Exception while creating the report");
        }

    }
}
