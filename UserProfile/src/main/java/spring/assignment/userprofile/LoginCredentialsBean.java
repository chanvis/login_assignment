package spring.assignment.userprofile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginCredentialsBean {
    private String userName;
    private String emailAddress;
    private int port;


}
