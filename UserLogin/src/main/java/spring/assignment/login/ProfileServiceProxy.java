package spring.assignment.login;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="profile-service")
public interface ProfileServiceProxy {

    @GetMapping("/profile/{username}")
    public ProfileCredentialsBean getProfileDetails(@PathVariable("username") String username);

    @PostMapping("saveProfileDetails/{userProfile}")
    public ProfileCredentialsBean saveProfileDetails(@PathVariable("userProfile") ProfileCredentialsBean profileCredentialsBean);


}
