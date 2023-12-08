package com.example.rraretailbusiness.domain;
import com.example.rraretailbusiness.dao.HibernateUtil;

public class TestModel {

    public TestModel() {
    }
    public static void main(String[] args){
        HibernateUtil.getSessionFactory().openSession();
    }
}
