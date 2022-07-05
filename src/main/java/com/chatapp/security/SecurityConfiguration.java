package com.chatapp.security;

import com.chatapp.service.socket.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/modify-password").hasAnyRole("ADMIN", "CHATTER")//???
                .mvcMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/chatter/**").hasRole("CHATTER")
                .mvcMatchers(HttpMethod.POST, "/chatter/**").hasRole("CHATTER")
                .anyRequest().permitAll();

        http.csrf().disable();
        return http.build();

    }
}