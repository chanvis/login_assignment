package spring.assignment.login.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import spring.assignment.login.NotFoundException;
import spring.assignment.login.ProfileCredentialsBean;
import spring.assignment.login.ProfileServiceProxy;
import spring.assignment.login.model.LoginCredentials;
import spring.assignment.login.repository.LoginRepository;

import java.security.Principal;
import java.util.HashMap;

@RestController
@Slf4j
public class LoginController  {

    @Autowired
    private ProfileServiceProxy proxy;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private Environment environment;

    HashMap<Integer, Integer> htPuerto=new HashMap<>();

    @RequestMapping({"/home","/"})
    public ModelAndView getHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    @RequestMapping("/hello")
    public ModelAndView getHello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello.html");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }


    @GetMapping(value="/{username}",produces={"application/json"})
    public LoginCredentials getCredentials(@PathVariable String username) {
        log.info("entered get credentials");
        LoginCredentials  loginCredentials = loginRepository.findByUserName(username);//.orElseThrow(() -> new NotFoundException("username: "+username+" not found"));
        int port= Integer.parseInt(environment.getProperty("local.server.port")) ;
        loginCredentials.setPort(port);

        return loginCredentials;
    }

    @GetMapping("/profile")
    public ModelAndView getProfileDetails(Principal principal) {
        log.info("entered get country-eureka server");
        log.info(principal.toString());
        ProfileCredentialsBean response = proxy.getProfileDetails(principal.getName());
        htPuerto.put(response.getPort(), htPuerto.getOrDefault(response.getPort(),0)+1);
        ModelAndView mav = new ModelAndView("hello.html");
        mav.addObject("profileDetails", response);
        return mav;
    }

}



