package spring.assignment.login.service;

import spring.assignment.login.model.LoginCredentials;


public interface LoginService {

    LoginCredentials findByUserName(String username);

    LoginCredentials saveRegistrationDetails(LoginCredentials command);

}
