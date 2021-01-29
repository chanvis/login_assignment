package spring.assignment.login.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.assignment.login.NotFoundException;
import spring.assignment.common.ProfileCredentialsBean;
import spring.assignment.login.ProfileServiceProxy;
import spring.assignment.login.model.LoginCredentials;
import spring.assignment.login.service.LoginService;

import java.security.Principal;
import java.util.HashMap;

@RestController
@Slf4j
public class LoginController  {

    @Autowired
    private ProfileServiceProxy proxy;

    @Autowired
    private LoginService loginService;

    @Autowired
    private Environment environment;

    @Autowired
    private PasswordEncoder passwordEncoder;

    HashMap<Integer, Integer> htPuerto=new HashMap<>();

    @RequestMapping({"/home"})
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

    @RequestMapping("/logout")
    public ModelAndView LogOut(){
        log.info("entered logout");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logout", "Logged out");
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView getLogin(){
        log.info("get login entered");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @RequestMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register.html");
        modelAndView.addObject("profileCredentialsBean", new ProfileCredentialsBean());
        return modelAndView;
    }


    @RequestMapping(value="/loginUser",produces={"application/json"})
    public ModelAndView getCredentials(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("entered get credentials "+username);
        ModelAndView mav = new ModelAndView();
        try {
            LoginCredentials loginCredentials = loginService.findByUserName(username);//.orElseThrow(() -> new NotFoundException("username: "+username+" not found"));
            if (loginCredentials != null && !passwordEncoder.matches(password, loginCredentials.getPassword())) {
                log.info("password did not match");
                mav.addObject("error", "Invalid password");
                mav.setViewName("redirect:http://localhost:8081/login");
            }
            if (loginCredentials != null && passwordEncoder.matches(password, loginCredentials.getPassword())) {
                log.info("password match");
                mav.addObject("username", username);
                mav.setViewName("redirect:http://localhost:8081/profile/profile/{username}");
            }
        }catch (Exception e){
            log.info("Invalid user");
            mav.addObject("error", "Invalid");
            mav.setViewName("redirect:http://localhost:8081/login");
        }
        return mav;
    }

    //@RequestMapping(value = {"/profile","/","/hello.html"}, method = RequestMethod.POST)
//    @RequestMapping({"/profile/{username}","/hello.html"})
//    public ModelAndView getProfileDetails(@PathVariable String username) {
//        log.info("entered get country-eureka server");
//        ProfileCredentialsBean response = proxy.getProfileDetails(username);
//        htPuerto.put(response.getPort(), htPuerto.getOrDefault(response.getPort(),0)+1);
//        ModelAndView mav = new ModelAndView("hello.html");
//        mav.addObject("profileDetails", response);
//        return mav;
//    }



    @PostMapping(value="/saveregister",consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ModelAndView saveRegistrationDetails(ProfileCredentialsBean profileCredentialsBean){
        log.info("entered save register-controller");
        log.info(profileCredentialsBean.getEmailAddress());
        ModelAndView modelAndView = new ModelAndView();

        LoginCredentials loginCredentials=new LoginCredentials();
        loginCredentials.setUserName(profileCredentialsBean.getUsername());
        loginCredentials.setEmailAddress(profileCredentialsBean.getEmailAddress());
        //loginCredentials.setPassword(passwordEncoder.encode("password"));
        loginCredentials.setPassword(passwordEncoder.encode(profileCredentialsBean.getPassword()));
        loginCredentials.setRole("ROLE_USER");
        loginCredentials.setLoginAttempt(0);
        LoginCredentials savedDetails=loginService.saveRegistrationDetails(loginCredentials);
        ProfileCredentialsBean profileBean =new ProfileCredentialsBean();
        profileBean.setUsername(profileCredentialsBean.getUsername());
        profileBean.setEmailAddress(profileCredentialsBean.getEmailAddress());
        profileBean.setAddress(profileCredentialsBean.getAddress());
        profileBean.setFirstName(profileCredentialsBean.getFirstName());
        profileBean.setLastName(profileCredentialsBean.getLastName());
        ProfileCredentialsBean response = proxy.saveProfileDetails(profileBean);
        modelAndView.setViewName("details.html");
        return modelAndView;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



}



