package com.project.kupuvalnik.configuration;

import com.project.kupuvalnik.models.entity.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //allow access to static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // access to pages for everyone
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("/stats").hasRole(UserRoleEnum.ADMIN.name())
                //we forbid all other pages for unauthenticated users
                .antMatchers("/**").authenticated()
                .and()
                //configure login from html form with username and password input field
                .formLogin()
                .loginPage("/users/login")
                //this is the input from username in the login template
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                //this is the input from password in the login template
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //where to go when the login is succeeded
                .defaultSuccessUrl("/")
                //if there is an error in the login
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                //logout url
                .logoutUrl("/users/logout")
                //where to go when logged out
                .logoutSuccessUrl("/")
                //remove the session from server
                .invalidateHttpSession(true)
                //delete the cookie from the session
                .deleteCookies("JSESSIONID");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
