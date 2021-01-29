//package spring.assignment.login.webconfiguration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import javax.sql.DataSource;
//
////@Configuration
////@EnableWebSecurity
//@Slf4j
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        log.info("Entered configure method");
//        http
//                .authorizeRequests()
//                //.antMatchers("/", "/home", "/register","/details","/saveregister","/h2-console/**").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("http://localhost:8081/profile/profile/{username}", true)
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//
//        http.csrf()
//                .ignoringAntMatchers("/h2-console/**");
//        http.headers()
//                .frameOptions()
//                .sameOrigin();
//    }
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select user_name, password, 'true' as enabled from login_credentials where user_name=?")
//                .authoritiesByUsernameQuery("select user_name, role from login_credentials where user_name=?")
//        ;
//    }
//
//
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("password")
////                        .roles("USER")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//}
