package spring.assignment.userprofile.repository;

import org.springframework.data.repository.CrudRepository;
import spring.assignment.userprofile.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    UserProfile findByUserName(String username);

}
