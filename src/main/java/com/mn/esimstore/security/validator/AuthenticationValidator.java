package com.mn.esimstore.security.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mn.esimstore.security.exception.SecurityException;
import com.mn.esimstore.security.jwt.JwtTokenValidator;
import com.mn.esimstore.security.user.SecurityUser;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationValidator {
    private final List<TokenValidator> validators;

    public AuthenticationValidator() {
        this.validators = new ArrayList<>();
        this.validators.add(new JwtTokenValidator());
    }

    public SecurityUser validate(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null)
            throw new SecurityException("Token not found");

        final String[] parts = token.split(" ");
        String prefix;

        if (parts.length == 2) {
            prefix = parts[0];
            token = parts[1];
        } else
            throw new SecurityException("Unknown token");

        TokenValidator validator = null;
        for (final TokenValidator item : this.validators) {
            if (prefix.equalsIgnoreCase(item.getPrefix())) {
                validator = item;
                break;
            }
        }
        if (validator == null)
            throw new SecurityException("Not supported token");

        final SecurityUser user = validator.validate(token, "secret");
        if (user.getClaims().get("aud") == null)
            throw new SecurityException("Audience not found");

        List<String> audiences = null;

        if (user.getClaims().get("aud") instanceof ArrayList) {
            audiences = (ArrayList<String>) user.getClaims().get("aud");
        } else {
            audiences = new ArrayList<>();
            audiences = Arrays.asList(user.getAudience().toLowerCase().split(","));
        }
        if (audiences == null || audiences.size() == 0
                || !audiences.contains("esimstore")) {
            throw new SecurityException("Forbidden token");
        }
        return user;
    }

    public void addValidator(final TokenValidator validator) {
        for (final TokenValidator item : this.validators) {
            if (item.getPrefix().equalsIgnoreCase(validator.getPrefix())) {
                return;
            }
        }
        this.validators.add(validator);
    }
}
