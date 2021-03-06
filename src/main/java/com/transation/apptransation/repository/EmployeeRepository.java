package com.transation.apptransation.repository;


import com.transation.apptransation.entity.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByPassportPassportNumber(String number);
}
