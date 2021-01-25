package spring.assignment.login.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.assignment.login.model.LoginCredentials;
import spring.assignment.login.repository.LoginRepository;


@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginCredentials findByUserName(String username) {
        LoginCredentials loginCredentialsOptional = loginRepository.findByUserName(username);

        if (null==loginCredentialsOptional) {
            throw new RuntimeException("credentials Not Found!");
        }

        return loginCredentialsOptional;
    }

    @Override
    public LoginCredentials saveRegistrationDetails(LoginCredentials command) {
        LoginCredentials saveLogin = loginRepository.save(command);
        log.debug("Saved Login:" + saveLogin.getId());
        return saveLogin;
    }
}
