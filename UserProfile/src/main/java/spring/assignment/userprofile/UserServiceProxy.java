package spring.assignment.userprofile;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="login-service")
public interface UserServiceProxy {

    @GetMapping("/{username}")
    public LoginCredentialsBean getCredentials(@PathVariable("username") String username);
}
