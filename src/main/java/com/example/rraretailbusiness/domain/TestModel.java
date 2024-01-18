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

            // Load and compile the second report
            JasperDesign design2 = JRXmlLoader.load("src/main/resources/reports/Purchase.jrxml");
            JasperReport jasperReport2 = JasperCompileManager.compileReport(design2);
            JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, null, connection);
            if (jasperPrint2.getPages().isEmpty()) {
                System.out.println("No pages in the second report");
            }
            JasperViewer.viewReport(jasperPrint2, false);

            // Load and compile the third report
            JasperDesign design3 = JRXmlLoader.load("src/main/resources/reports/ItemRep.jrxml");
            JasperReport jasperReport3 = JasperCompileManager.compileReport(design3);
            JasperPrint jasperPrint3 = JasperFillManager.fillReport(jasperReport3, null, connection);
            if (jasperPrint3.getPages().isEmpty()) {
                System.out.println("No pages in the second report");
            }
            JasperViewer.viewReport(jasperPrint3, false);
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
