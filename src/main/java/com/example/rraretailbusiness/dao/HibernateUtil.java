package com.example.rraretailbusiness.dao;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            return (new Configuration()).configure().buildSessionFactory();
        }catch (Throwable varx){
            System.err.println("Initial SessionFactory creation failed." + varx);
            throw new ExceptionInInitializerError(varx);
        }


    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
