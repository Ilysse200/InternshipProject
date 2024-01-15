/**
 * The following is the CRUD defined for the ItemFlow table
 */

package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.ItemFlow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemFlowDao {

    //The following will save information in the ItemFlow table
    public String saveItemFlow(ItemFlow itemFlow){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            // Disable foreign key constraints
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            session.save(itemFlow);
            transaction.commit();
            session.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "ItemFlow saved successfully";
    }
    // Method to calculate total sales for a specific item
    public int getTotalSalesForItem(Long itemId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Long> query = session.createQuery(
                    "SELECT SUM(IF.totalPrice) FROM ItemFlow IF WHERE IF.itemList.itemCode = :itemId AND IF.status = 'OUT'",
                    Long.class
            );
            query.setParameter("itemId", itemId);
            Long totalSales = query.uniqueResult();
            return totalSales != null ? totalSales.intValue() : 0;
        } finally {
            session.close();
        }
    }

    // Method to calculate total purchases for a specific item
    public int getTotalPurchasesForItem(Long itemId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Long> query = session.createQuery(
                    "SELECT SUM(IF.totalPrice) FROM ItemFlow IF WHERE IF.itemList.itemCode = :itemId AND IF.status = 'IN'",
                    Long.class
            );
            query.setParameter("itemId", itemId);
            Long totalPurchases = query.uniqueResult();
            return totalPurchases != null ? totalPurchases.intValue() : 0;
        } finally {
            session.close();
        }
    }


    //The method below will enable us to display all itemFlows records from its relative table
    public List<ItemFlow> displayAllEmployees(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM ItemFlow ", ItemFlow.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the ItemFlow exists
    public boolean findItemFlowId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM ItemFlow where itemFlowId =:id");
            query.setParameter("Id", Id);

            ItemFlowDao itemFlow = (ItemFlowDao) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    //The following method will delete any row in the ItemFlow table based on the result of a query

    public boolean deleteItemFlow(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM ItemFlow  where itemFlowId=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single querry or else null
            ItemFlowDao itemFlow = (ItemFlowDao) query.uniqueResult();
            if(itemFlow!= null){
                session.delete(itemFlow);
                transaction.commit();
                session.close();
                return true;
            }

            else{

                transaction.rollback();
                session.close();
                return false;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;

    }


    //This method will help us delete all employees from the table
//    public String deleteAllItemFlows(ItemFlow itemFlow){
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            transaction.commit();
//            session.delete(itemFlow);
//
//            transaction.commit();
//            session.close();
//
//            return "itemFlow deleted successfully";
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return "ItemFlow not deleted";
//
//
//    }

    /*The following method updates the itemFlows data in the ItemFlow table,
     with exception of the ItemFlowId
    */
    public boolean UpdateItemFlow(ItemFlow itemFlow, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM ItemFlow  WHERE itemFlowId= :Id");
            query.setParameter("Id", Id);

            itemFlow = (ItemFlow) query.uniqueResult();


            if ((findItemFlowId(Id))==true){


                session.update(itemFlow);
                transaction.commit();
                session.close();
                return true;

            }
            else {
                transaction.rollback();
                session.close();
                return false;

            }


        } catch (Exception ex) {
            ex.printStackTrace();

        };
        return false;
    }
}
