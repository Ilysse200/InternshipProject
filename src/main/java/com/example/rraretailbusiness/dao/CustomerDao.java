/**
 * The following is the CRUD defined for the customer table
 */
package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {

    public String saveCustomer(Customer customer){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            session.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "customer saved successfully";
    }


    //The method below will enable us to display all customers records from its related table
    public List<Customer> displayAllCustomers(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Customer ", Customer.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the customer exists
    public boolean findCustomerId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Customer where customerId =:id");
            query.setParameter("Id", Id);

            Customer customer = (Customer) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteCustomer(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Customer  where customerId=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single querry or else null
            Customer customer = (Customer) query.uniqueResult();
            if(customer!= null){
                session.delete(customer);
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


    //This method will help us delete all Customers, from the table
//    public String deleteCustomers(Customer customer){
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            transaction.commit();
//            session.delete(customer);
//
//            transaction.commit();
//            session.close();
//
//            return "Customer deleted successfully";
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return "Customer not deleted";
//
//
//    }

    /*The following method updates the Customer data in the customer table,
     with exception of the employeeId
    */
    public boolean UpdateCustomer(Customer customer, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Customer  WHERE customerId= :Id");
            query.setParameter("Id", Id);

            customer = (Customer) query.uniqueResult();


            if ((findCustomerId(Id))==true){


                session.update(customer);
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

