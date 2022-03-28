package com.transation.apptransation.service;


import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.entity.Passport;
import com.transation.apptransation.exception.ServiceException;
import com.transation.apptransation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
            throw new ServiceException(" employee cant find all" + e);
        }


    }

    @Override
    public void saveEmployee(Employee employee) throws ServiceException {
        try {
            Passport passport = employee.getPassport();
            if (passport.getPassportNumber() != null) {


                passport.setPassportNumber(passport.getPassportNumber().toUpperCase());
            }
            this.employeeRepository.save(employee);
        } catch (DataAccessException e) {
            throw new ServiceException("employee cant save", e);
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
            throw new ServiceException("employee cant delete by id " + e);
        }


    }

    @Override
    public Employee findStudentByNumber(String passportNumber) {


        Employee employee = employeeRepository.findByPassportPassportNumber(passportNumber.toUpperCase());
        if (employee == null) {
            return new Employee();
        }
        return employee;
    }


}
