package com.mn.esimstore.security.jwt;

import java.io.UnsupportedEncodingException;

import com.mn.esimstore.security.exception.SecurityException;
import com.mn.esimstore.security.user.SecurityUser;
import com.mn.esimstore.security.validator.TokenValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtTokenValidator implements TokenValidator {

    @Override
    public String getPrefix() {
        return "Bearer";
    }

    @Override
    public SecurityUser validate(String token, String secret) throws SecurityException {
        try {
            JwtToken jwtToken = new JwtToken(token);
            jwtToken.validate(secret);
            SecurityUser user = new SecurityUser();
            user.setClaims(jwtToken.getClaims());
            return user;
        } catch (ExpiredJwtException ex) {
            throw new SecurityException("Token expired");
        } catch (UnsupportedJwtException ex) {
            throw new SecurityException("Unsupported token");
        } catch (MalformedJwtException ex) {
            throw new SecurityException("Malformed token");
        } catch (SignatureException ex) {
            throw new SecurityException("Invalid token");
        } catch (IllegalArgumentException ex) {
            throw new SecurityException("Illegal token");
        } catch (UnsupportedEncodingException ex) {
            throw new SecurityException("Unsupported encoding");
        }

    }
}
