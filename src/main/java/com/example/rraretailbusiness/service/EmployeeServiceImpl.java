package com.example.rraretailbusiness.service;

import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.domain.Employee;

import java.rmi.RemoteException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao dao = new EmployeeDao();

    @Override
    public String saveEmployee(Employee employee){ return dao.saveEmployee(employee);}

    @Override
    public List<Employee> displayAllEmployees() {
        return dao.displayAllEmployees();
    }

    @Override
    public Employee findEmployeeId(Long Id) {
        return dao.findEmployeeId(Id);
    }

    @Override
    public boolean deleteEmployee(Long Id) {
        return dao.deleteEmployee(Id);
    }

    @Override
    public boolean UpdateEmployee(Employee employee, Long Id) {
        return dao.UpdateEmployee(employee, Id);
    }
}
