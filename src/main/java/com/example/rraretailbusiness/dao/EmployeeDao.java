/**
 * The following is the CRUD defined for the employee table
 */


package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {



    //The saveEmployee  will enable us to save the data in employee table
    public String saveEmployee(Employee employee){
            try{
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.save(employee);
                transaction.commit();
                session.close();


            }catch (Exception ex){
                ex.printStackTrace();
            }
        return "employee saved successfully";
        }


        //The method below will enable us to display all employees records from its relative table
    public List<Employee> displayAllEmployees(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Employee", Employee.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //This method will help us to check whether the employee exists
    public boolean findEmployeeId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Employee where empID =:id");
            query.setParameter("Id", Id);

            Employee employee = (Employee) query.uniqueResult();


            transaction.commit();
            session.close();

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }






    /*This method helps us to delete an employee based on it's Id. Before deleting, we first make sure
      that the employee exists in the table
     */

    public boolean deleteEmployee(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Employee  where empID=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single querry or else null
            Employee employee = (Employee) query.uniqueResult();
            if(employee!= null){
                session.delete(employee);
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
//    public String deleteAllEmployees(Employee employee){
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            transaction.commit();
//            session.delete(employee);
//
//            transaction.commit();
//            session.close();
//
//            return "employee deleted successfully";
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//        return "Employee not deleted";
//
//
//    }

    /*The following method updates the employee data in the employee table,
     with exception of the employeeId
    */
    public boolean UpdateEmployee(Employee employee, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Employee  WHERE empID= :Id");
            query.setParameter("Id", Id);

            employee = (Employee) query.uniqueResult();


            if ((findEmployeeId(Id))==true){


                session.update(employee);
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
