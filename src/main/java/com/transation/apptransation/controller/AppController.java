package com.transation.apptransation.controller;


import com.transation.apptransation.entity.Role;
import com.transation.apptransation.entity.User;
import com.transation.apptransation.repository.UserRepository;
import com.transation.apptransation.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@Controller
@Log4j2
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage(Model model, HttpServletRequest request) {

        Locale locale = request.getLocale();
        String countryCode = locale.getCountry();
        String countryName = locale.getDisplayCountry();
        String langeCode = locale.getLanguage();
        String langeName = locale.getDisplayLanguage();
        log.info(countryCode + ":" + countryName);
        log.info(langeCode + ":" + langeName);

        String[] languages = Locale.getISOLanguages();
        for (String language : languages) {
            Locale local = new Locale(language);
            log.info(language + ":" + local.getDisplayLanguage());
        }
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) throws ServiceException {
        try {
            model.addAttribute("user", new User());

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "signup_form";
    }

    @GetMapping("/registerAdmin")
    public String showRegistrationFormAdmin(Model model) throws ServiceException {

        try {
            model.addAttribute("user", new User());

        } catch (RuntimeException e) {
            throw new ServiceException(e.getMessage());
        }
        return "signup_form_admin";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(new Role("USER"));
        if (user == userRepo.save(user)) {
            return "register_success";
        } else
            return "/main/resources/templates/error.html";

    }


    @PostMapping("/process_register_admin")
    public String processRegisterAdmin(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(new Role("ADMIN"));
        if (user == userRepo.save(user)) {
            return "register_success";
        } else
            return "/main/resources/templates/error.html";

    }

    @GetMapping("/getOffice")
    public ModelAndView getOffice() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("office");
        return modelAndView;


    }

}













