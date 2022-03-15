package com.transation.apptransation.service;


import com.transation.apptransation.details.CustomUserDetails;
import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.entity.User;
import com.transation.apptransation.repository.EmployeeRepository;
import com.transation.apptransation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() throws ServiceException {
        try {
            return employeeRepository.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException(" employee cant find all");
        }


    }

    @Override
    public void saveEmployee(Employee employee) throws ServiceException {
        try {
            this.employeeRepository.save(employee);
        } catch (DataAccessException e) {
            throw new ServiceException("employee cant save");
        }

    }

    @Override
    public Employee getEmployeeById(long id) throws ServiceException {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new ServiceException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) throws ServiceException {
        try {
            this.employeeRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("employee cant delete by id ");
        }


    }

    @Override
    public Employee findStudentByNumber(String passportNumber) throws ServiceException {


          Employee employee= employeeRepository.findByPassportNumber(passportNumber);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return employee;
    }








}
