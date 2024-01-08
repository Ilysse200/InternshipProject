/**
 * The following is the CRUD defined for the item table
 */

package com.example.rraretailbusiness.dao;

import com.example.rraretailbusiness.domain.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDao {
    public String saveItem(Item item){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            // Disable foreign key constraints
//            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
            session.save(item);
            transaction.commit();
            session.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "item saved successfully";
    }


    //The method below will enable us to display all employees records from its relative table
    public List<Item> displayAllEmployees(){
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Item ", Item.class).list();



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Item findItemByName(String itemName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Use HQL (Hibernate Query Language) to retrieve the item by name
            String hql = "FROM Item WHERE itemName = :itemName";
            Query<Item> query = session.createQuery(hql, Item.class);
            query.setParameter("itemName", itemName);

            List<Item> items = query.getResultList();

            // Check if the item was found
            if (!items.isEmpty()) {
                return items.get(0); // Assuming itemName is unique
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if the item is not found or an error occurs
    }

    //This method will help us to check whether the employee exists
    public Item findItemId(Long Id){

        try{

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Item where itemCode =:id");
            query.setParameter("id", Id);

            Item item = (Item) query.uniqueResult();
            System.out.println("Found Item: " + item);


            transaction.commit();
            session.close();

            return item;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean deleteItem(Long id){
        try{
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Item  where itemCode=:id");
            query.setParameter("id",id);

            //The following Hibernate method uniqueResult will return a single querry or else null
            Item item = (Item) query.uniqueResult();
            if(item!= null){
                session.delete(item);
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
    public boolean UpdateItem(Item item, Long Id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Item  WHERE itemCode= :Id");
            query.setParameter("Id", Id);

            item = (Item) query.uniqueResult();
            ItemDao itemDao = new ItemDao();
            Item item1 = itemDao.findItemId(Id);



            if (item1 !=null){


                session.update(item);
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
