package spring.assignment.login.repository;

import org.springframework.data.repository.CrudRepository;
import spring.assignment.login.model.LoginCredentials;

public interface LoginRepository extends CrudRepository<LoginCredentials, Long> {

    LoginCredentials findByUserName(String username);


}
