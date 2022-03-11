package com.transation.apptransation.controller;


import com.transation.apptransation.entity.User;
import com.transation.apptransation.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@Controller
@Log4j2
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage(Model model, HttpServletRequest request) {

        Locale locale=request.getLocale();
        String countryCode = locale.getCountry();
        String countryName = locale.getDisplayCountry();
        String langeCode =locale.getLanguage();
        String langeName = locale.getDisplayLanguage();
        log.info(countryCode+":"+countryName);
        log.info(langeCode+":"+langeName);

        String [] languages =Locale.getISOLanguages();
        for (String language : languages){
            Locale local=new Locale(language);
            log.info(language + ":"+ local.getDisplayLanguage());
        }


        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (user==userRepo.save(user)){
            return "register_success";
        }else
            return "error";

    }














}