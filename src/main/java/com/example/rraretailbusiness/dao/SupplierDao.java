/**
 * The following is the CRUD defined for the supplier table
 */

package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDao {
    public String saveSupplier(Supplier supplier){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(supplier);
            transaction.commit();
            session.close();

            return "Supplier saved successfully";

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "Saving of the supplier failed";

    }
    //The method below will enable us to display all customers records from its related table
    public List<Supplier> displayAllSuppliers(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Supplier ", Supplier.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the supplier exists
    public boolean findSupplierId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Supplier where supplierId =:id");
            query.setParameter("Id", Id);

            Supplier supplier = (Supplier) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Supplier  where supplierId=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single query or else null
            Supplier supplier = (Supplier) query.uniqueResult();
            if(supplier!= null){
                session.delete(supplier);
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


//    //This method will help us delete all employees from the table
//    public String deleteAllSuppliers(Supplier supplier){
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            transaction.commit();
//            session.delete(supplier);
//
//            transaction.commit();
//            session.close();
//
//            return "Supplier deleted successfully";
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return "Supplier not deleted";
//
//
//    }

    /*The following method updates the employee data in the employee table,
     with exception of the employeeId
    */
    public boolean UpdateSupplier(Supplier supplier, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Supplier  WHERE supplierId= :Id");
            query.setParameter("Id", Id);

            supplier = (Supplier) query.uniqueResult();


            if ((findSupplierId(Id))==true){


                session.update(supplier);
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
