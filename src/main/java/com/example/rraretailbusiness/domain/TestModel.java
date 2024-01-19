package com.example.rraretailbusiness.domain;
import com.example.rraretailbusiness.dao.HibernateUtil;
import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import com.codingerror.model.AddressDetails;
import com.codingerror.model.HeaderDetails;
import com.codingerror.model.Product;
import com.codingerror.model.ProductTableHeader;
import com.codingerror.service.CodingErrorPdfInvoiceCreator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TestModel {

    public TestModel() {
    }

    public static void main(String[] args) throws FileNotFoundException {
            Session session =HibernateUtil.getSessionFactory().openSession();
            LocalDate ld= LocalDate.now();
            String pdfName= ld+".pdf";
            CodingErrorPdfInvoiceCreator cepdf=new CodingErrorPdfInvoiceCreator(pdfName);
            cepdf.createDocument();

            //Create Header start
            HeaderDetails header=new HeaderDetails();
            header.setInvoiceNo("RK35623").setInvoiceDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build();
            cepdf.createHeader(header);
            //Header End

            //Create Address start
            AddressDetails addressDetails=new AddressDetails();
            addressDetails
                    .setBillingCompany("Coding Error")
                    .setBillingName("Bhaskar")
                    .setBillingAddress("Bangluru,karnataka,india\n djdj\ndsjdsk")
                    .setBillingEmail("codingerror303@gmail.com")
                    .setShippingName("Customer Name \n")
                    .setShippingAddress("Banglore Name sdss\n swjs\n")
                    .build();

            cepdf.createAddress(addressDetails);
            //Address end

            //Product Start
            ProductTableHeader productTableHeader=new ProductTableHeader();
            cepdf.createTableHeader(productTableHeader);
            ItemFlow itemFlow = new ItemFlow();
            ItemFlowDao itemFlowDao = new ItemFlowDao();

            //create necessary variables to access specific values in the db
            int quantities=0, unitPrices=0;
            String itemName = "";

            // Create an ArrayList to store ItemData
            List<ItemData> itemList = new ArrayList<>();
            List<Product> productList = new ArrayList<>();
            List<ItemFlow> itemFlows=itemFlowDao.displayAllEmployees();
            for(ItemFlow itemFlow1: itemFlows){
                    if(itemFlow1!=null){
                            ItemData itemData = new ItemData(
                                    itemFlow1.getItemList().getItemName(),
                                    itemFlow1.getQuantity(),
                                    itemFlow1.getUnitPrice()

                            );
                            itemList.add(itemData);
                    }


            }

            productList=cepdf.modifyProductList(productList);
//            cepdf.createProduct(itemList);
//            //Product End

            //Term and Condition Start
            List<String> TncList=new ArrayList<>();
            TncList.add("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.");
            TncList.add("2. The Seller warrants the product for one (1) year from the date of shipment");
            String imagePath="src/main/resources/ce_logo_circle_transparent.png";
            cepdf.createTnc(TncList,false,imagePath);
            // Term and condition end
            System.out.println("pdf genrated");
        }
    }


