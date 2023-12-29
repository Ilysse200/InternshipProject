package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Sales;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SalesDao {
    public String saveSales(Sales sales){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            // Disable foreign key constraints
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            session.save(sales);
            transaction.commit();
            session.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "sales saved successfully";
    }


    //The method below will enable us to display all sales records from its related table
    public List<Sales> displayAllSales(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Sales ", Sales.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the sales exists
    public boolean findSalesId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Sales where  salesID =:id");
            query.setParameter("Id", Id);

            Sales sales = (Sales) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteSales(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Sales  where  salesID=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single query or else null
            Sales sales = (Sales) query.uniqueResult();
            if(sales!= null){
                session.delete(sales);
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


    //This method will help us delete all sales from the table
//    public String deleteAllSaless(Sales sales){
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            transaction.commit();
//            session.delete(sales);
//
//            transaction.commit();
//            session.close();
//
//            return "sales deleted successfully";
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return "sales not deleted";
//
//
//    }

    /*The following method updates the sales data in the Sales table,
     with exception of the salesId
    */
    public boolean UpdateSales(Sales sales, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Sales  WHERE salesID= :Id");
            query.setParameter("Id", Id);

            sales = (Sales) query.uniqueResult();


            if ((findSalesId(Id))==true){


                session.update(sales);
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
