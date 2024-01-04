package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.domain.Employee;

import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeService {


    String saveEmployee(Employee employee) ;
    List<Employee> displayAllEmployees();
    Employee findEmployeeId(Long Id);
    boolean deleteEmployee(Long Id);
    boolean UpdateEmployee(Employee employee, Long Id);


}
