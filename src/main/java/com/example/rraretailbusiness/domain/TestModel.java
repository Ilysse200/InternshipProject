package com.example.rraretailbusiness.domain;
import com.example.rraretailbusiness.dao.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;


public class TestModel {

    public TestModel() {
    }
    public static void main(String[] args){

        try {
            Session session =HibernateUtil.getSessionFactory().openSession();
            Connection connection = session.doReturningWork(connection1 -> connection1);

            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/Sales.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
             JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, null, connection );
            if (jasperPrint.getPages().isEmpty()) {
                System.out.println("No pages in the report");
            }
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
