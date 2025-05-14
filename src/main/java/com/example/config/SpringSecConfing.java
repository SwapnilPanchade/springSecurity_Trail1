package com.example.config;

import com.example.Service.MyUserDetailsService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.websocket.Session;
import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class SpringSecConfing {


    @Autowired
    private MyUserDetailsService userDetailsService;


    //! so we are basically cofinguring our security chain here like the number of factors we are taking into consideration here are as following
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(Customizer -> Customizer.disable()).authorizeHttpRequests(request -> request.antMatchers("/register").permitAll().
                        anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

        //? well again we can use httprequest.formlogin(Customizer.withDeafaults()); to implement the form login security via broweser login if we are authenticating using browser
    }


    //! now will do same changes for authantication provider like the user to be authanticated

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
