package com.mn.esimstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mn.esimstore.security.validator.AuthenticationValidator;

@Configuration
public class SecurityConfigurerAdapter {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(header -> header.cacheControl(Customizer.withDefaults()))
                .exceptionHandling(handling -> handling.authenticationEntryPoint(authenticationErrorHandling()));
        AuthenticationManager authenticationManager = authenticationManager(
                httpSecurity.getSharedObject(AuthenticationConfiguration.class));
        httpSecurity.addFilterBefore(new AuthenticationFilter(authenticationValidator(), authenticationManager),
                BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationErrorHandling authenticationErrorHandling() {
        return new AuthenticationErrorHandling();
    }

    @Bean
    public AuthenticationValidator authenticationValidator() {
        return new AuthenticationValidator();
    }
}
