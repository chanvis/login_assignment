package spring.assignment.login;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.assignment.common.ProfileCredentialsBean;

@FeignClient(name="profile")
public interface ProfileServiceProxy {

    @GetMapping("/profile/{username}")
    public ProfileCredentialsBean getProfileDetails(@PathVariable("username") String username);

    @PostMapping(value="/saveProfileDetails")
    public ProfileCredentialsBean saveProfileDetails( ProfileCredentialsBean profileCredentialsBean);


}
