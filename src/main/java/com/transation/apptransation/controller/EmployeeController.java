package com.transation.apptransation.controller;


import com.transation.apptransation.entity.Employee;
import com.transation.apptransation.service.EmployeeService;

import com.transation.apptransation.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/users")
    public String viewHomePage(Model model) throws Exception {
        try {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "index_app";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) throws Exception {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) throws Exception {
        try {
            employeeService.saveEmployee(employee);
        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) throws Exception {
        try {

            Employee employee = employeeService.getEmployeeById(id);
            model.addAttribute("employee", employee);
        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) throws Exception {
        try {

            this.employeeService.deleteEmployeeById(id);

        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:/users";
    }


    @GetMapping("/myProfile")
    public String myProfile(Model model) throws Exception {
        try {
            model.addAttribute("student", employeeService.getAllEmployees());

        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }
        return "/profile";
    }





    @PostMapping("/findStudent")
    public String findStudent(Model model,@Param("name") String name) throws Exception {
        try {
            model.addAttribute("name", employeeService.findStudentByName(name));

        } catch (ServiceException e) {
            throw new Exception(e.getMessage());
        }

        return "redirect:my_data";
    }


}
