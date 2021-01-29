//package spring.assignment.zuulproxy;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.util.matcher.RegexRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableWebSecurity(debug = true)
////@EnableResourceServer
//public class GatewayConfiguration extends WebSecurityConfigurerAdapter  {
//
//
//    @Override
//    public void configure(final HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/login/**","/loginUser/**")
////                .permitAll()
////                .antMatchers("/profile/**")
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                //.defaultSuccessUrl("http://localhost:8081/profile/profile/user", true)
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//
//        http.authorizeRequests()
//                //.antMatchers("/loginUser/**")
//                //.permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("http://localhost:8081/profile/profile/user", true)
//                .permitAll();
//    }
//
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(EndpointRequest.to("loginUser"));
//    }
//
////    @Autowired
////    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
////                .dataSource(dataSource)
////                .usersByUsernameQuery("select user_name, password, 'true' as enabled from login_credentials where user_name=?")
////                .authoritiesByUsernameQuery("select user_name, role from login_credentials where user_name=?")
////        ;
////    }
//
//}
