package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Item;
import com.example.rraretailbusiness.domain.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PurchaseDao {

    public String savePurchase(Purchase purchase){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            // Disable foreign key constraints
//            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            session.save(purchase);
            transaction.commit();
            session.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "purchase saved successfully";
    }


    //The method below will enable us to display all purchase records from its related  table
    public List<Purchase> displayAllPurchases(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Purchase ", Purchase.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the employee exists
    public boolean findPurchaseId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Purchase where purchaseId =:id");
            query.setParameter("Id", Id);

           Purchase purchase = (Purchase) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }


    public boolean deletePurchase(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Purchase  where purchaseId=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single querry or else null
            Purchase purchase = (Purchase) query.uniqueResult();
            if(purchase!= null){
                session.delete(purchase);
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
    public String deleteAllPurchases(Purchase purchase){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            session.delete(purchase);

            transaction.commit();
            session.close();

            return "purchase deleted successfully";

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "purchase not deleted";


    }

    /*The following method updates the employee data in the employee table,
     with exception of the employeeId
    */
    public boolean UpdatePurchase(Purchase purchase, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Purchase  WHERE purchaseId= :Id");
            query.setParameter("Id", Id);

            purchase = (Purchase) query.uniqueResult();


            if ((findPurchaseId(Id))==true){


                session.update(purchase);
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
