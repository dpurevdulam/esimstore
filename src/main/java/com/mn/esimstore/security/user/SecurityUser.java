package com.mn.esimstore.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import io.jsonwebtoken.Claims;

public class SecurityUser implements UserDetails {

    private static final long serialVersion = 8229870872828100862L;

    private static final String ISSUER = "iss";
    private static final String AUDIENCE = "aud";
    private static final String EXPIRATION = "exp";
    private static final String EMAIL = "email";
    private static final String SUBJECT = "subject";

    private Map<String, Object> claims = new LinkedHashMap<String, Object>();

    public SecurityUser() {
        super();
    }

    public String getIssuer() {
        return (String) this.claims.get(ISSUER);
    }

    public void setIssuer(String issuer) {
        this.claims.put(ISSUER, issuer);
    }

    public String getAudience() {
        return (String) this.claims.get(AUDIENCE);
    }

    public void setAudience(String audience) {
        this.claims.put(AUDIENCE, audience);
    }

    public Date getExpiration() {
        return (Date) this.claims.get(EXPIRATION);
    }

    public void setExpiration(String expiration) {
        this.claims.put(EXPIRATION, expiration);
    }

    public String getEmail() {
        return (String) this.claims.get(EMAIL);
    }

    public void setEmail(String email) {
        this.claims.put(EMAIL, email);
    }

    public String getSubject() {
        return (String) this.claims.get(SUBJECT);
    }

    public void setSubject(String subject) {
        this.claims.put(SUBJECT, subject);
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.getSubject();
    }

}
