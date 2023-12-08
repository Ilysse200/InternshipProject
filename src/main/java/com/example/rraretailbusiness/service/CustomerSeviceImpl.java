package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.CustomerDao;
import com.example.rraretailbusiness.domain.Customer;
import com.example.rraretailbusiness.domain.Employee;

import java.util.List;

public class CustomerSeviceImpl implements CustomerService{

    CustomerDao dao = new CustomerDao();

    @Override
    public String saveCustomer(Customer customer) {
        return dao.saveCustomer(customer);
    }

    @Override
    public List<Customer> displayAllEmployees() {
        return dao.displayAllCustomers();
    }

    @Override
    public boolean findCustomerId(Long Id) {
        return dao.findCustomerId(Id);
    }

    @Override
    public boolean deleteCustomer(Long Id) {
        return dao.deleteCustomer(Id);
    }

    @Override
    public boolean UpdateCustomer(Customer customer, Long Id) {
        return dao.UpdateCustomer(customer,Id);
    }
}
