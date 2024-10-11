package com.mn.esimstore.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mn.esimstore.security.exception.ErrorType;
import com.mn.esimstore.security.exception.SecurityException;
import com.mn.esimstore.security.helper.Utils;
import com.mn.esimstore.security.user.SecurityUser;
import com.mn.esimstore.security.validator.AuthenticationValidator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends BasicAuthenticationFilter {
    private AuthenticationValidator validator;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public AuthenticationFilter(AuthenticationValidator validator, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.validator = validator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws JsonProcessingException, IOException, ServletException {
        try {
            SecurityUser user = validator.validate(request);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (SecurityException ex) {
            SecurityContextHolder.clearContext();
            Utils.sendResponse(response, ex.getErrorType());
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            Utils.sendResponse(response, ErrorType.UNAUTHORIZED.message(ex.getMessage()));
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            Utils.sendResponse(response, ErrorType.SERVICE_ERROR.message(ex.getMessage()));
        }
        chain.doFilter(request, response);
    }
}
