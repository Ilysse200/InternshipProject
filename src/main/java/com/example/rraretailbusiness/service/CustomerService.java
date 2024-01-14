package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Customer;
import com.example.rraretailbusiness.domain.Employee;

import java.util.List;

public interface CustomerService {

    String saveCustomer(Customer customer) ;
    List<Customer> displayAllEmployees();
    Customer findCustomerId(Long Id);
    boolean deleteCustomer(Long Id);
    boolean UpdateCustomer( Customer customer, Long Id);
}

