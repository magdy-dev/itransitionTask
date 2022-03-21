package com.transation.apptransation.service;


import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.exception.ServiceException;

import java.util.List;

public interface  EmployeeService {
    List<Employee> getAllEmployees() throws ServiceException;

    void saveEmployee(Employee employee) throws ServiceException;
//    void saveMessage(String message) throws ServiceException;

    Employee getEmployeeById(long id) throws ServiceException;

    void deleteEmployeeById(long id) throws ServiceException;

    Employee findStudentByNumber(String  passportNumber) throws ServiceException;


}
