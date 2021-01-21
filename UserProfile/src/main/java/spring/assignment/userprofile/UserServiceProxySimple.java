package spring.assignment.userprofile;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign",url="http://localhost:9092")
public interface UserServiceProxySimple {

    @GetMapping("/{username}")
    public LoginCredentialsBean getCredentials(@PathVariable("username") String username) ;


}
