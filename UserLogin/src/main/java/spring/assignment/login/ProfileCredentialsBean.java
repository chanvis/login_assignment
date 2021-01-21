package spring.assignment.login;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileCredentialsBean {

    private String firstName;
    private String lastName;
    private String address;
    private int port;


}
