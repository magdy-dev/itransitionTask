package com.transation.apptransation.controller;

import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.service.EmployeeServiceImpl;
import com.transation.apptransation.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfficeControler {
    @Autowired
    private EmployeeServiceImpl employeeService;


    @GetMapping("/office")
    public String vieOffice(Model model) throws Exception {
        try {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());

        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }
        return "office";
    }


    @GetMapping("/showFormForMessage/{id}")
    public String showFormForMessage(@PathVariable(value = "id") long id, Model model) throws Exception {
        try {

            Employee employee = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employee);
        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "update_employee";
    }



    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("employee") Employee employee) throws Exception {
        try {
            employeeService.saveEmployee(employee);
        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:/office";
    }


}
