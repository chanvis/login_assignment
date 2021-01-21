package spring.assignment.userprofile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.assignment.userprofile.model.UserProfile;
import spring.assignment.userprofile.repository.UserProfileRepository;

import java.util.HashMap;

@RestController
@Slf4j
public class UserProfileController {

    @Autowired
    private UserServiceProxy proxy;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private Environment environment;

    HashMap<Integer, Integer> htPuerto=new HashMap<>();

    @GetMapping("/{username}")
    public LoginCredentialsBean getCredentials(@PathVariable String username) {
        log.info("entered get country-eureka server");
        LoginCredentialsBean response = proxy.getCredentials(username);
        htPuerto.put(response.getPort(), htPuerto.getOrDefault(response.getPort(),0)+1);
        System.out.println("LoginCredentialsBean -> {} "+response);
        return response;
    }


    @Autowired
    private UserServiceProxySimple simpleProxy;
    @GetMapping("/feign/{username}")
    public LoginCredentialsBean getCredentialsUsingFeign(@PathVariable String username) {
        log.info("entered feign method"+username);
        LoginCredentialsBean response = simpleProxy.getCredentials(username);
        return response;
    }

    @GetMapping(value="/profile/{username}",produces={"application/json"})
    public UserProfile getProfileDetails(@PathVariable String username) {
        log.info("entered get getProfileDetails");
        UserProfile userProfile = userProfileRepository.findByUserName(username);//.orElseThrow(() -> new NotFoundException("username: "+username+" not found"));
        int port= Integer.parseInt(environment.getProperty("local.server.port")) ;
        userProfile.setPort(port);

        return userProfile;
    }


}
