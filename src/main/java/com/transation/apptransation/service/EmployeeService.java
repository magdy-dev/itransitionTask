package com.transation.apptransation.service;


import com.transation.apptransation.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees() throws Exception;
    void saveEmployee(Employee employee) throws ServiceException;
    Employee getEmployeeById(long id) throws ServiceException;
    void deleteEmployeeById(long id) throws ServiceException;
}
