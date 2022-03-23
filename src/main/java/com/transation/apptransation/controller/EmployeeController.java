package com.transation.apptransation.controller;


import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.service.EmployeeService;

import com.transation.apptransation.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/users")
    public String viewHomePage(Model model) throws ServiceException {
        try {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "index_app";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) throws ServiceException {

        try {
            Employee employee = new Employee();
            model.addAttribute("employee", employee);
        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) throws ServiceException {
        try {
            employeeService.saveEmployee(employee);

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) throws ServiceException {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employee);

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) throws Exception {
        try {
            this.employeeService.deleteEmployeeById(id);

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "redirect:/users";
    }


    @GetMapping("/myProfile")
    public String myProfile(Model model) throws ServiceException {
        try {
            model.addAttribute("student", employeeService.getAllEmployees());

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "/profile";
    }


    @PostMapping("/findStudent")
    public String findStudent(Model model, @Param("passportNumber") String passportNumber) throws ServiceException {
        try {

            if (passportNumber != null && !passportNumber.isEmpty()) {
                model.addAttribute("passportNumber", employeeService.findStudentByNumber(passportNumber));
            } else {
                return "/my_data";
            }
        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "/my_data";

    }


    @GetMapping("/visaList")
    public String visaList(Model model) throws ServiceException {
        try {
            model.addAttribute("list", employeeService.getAllEmployees());

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "visa_list";
    }


}
