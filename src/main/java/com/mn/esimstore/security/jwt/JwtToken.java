package com.mn.esimstore.security.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtToken {
    private String token;
    private Claims claims;

    public JwtToken() {
        super();
    }

    public JwtToken(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void validate(String secret) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
            SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        claims = (Claims) Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(this.token).getBody();
    }

    public String getIssuer() {
        return claims.getIssuer();
    }

    public Date getExpiration() {
        return claims.getExpiration();
    }

    public String getAudience() {
        return claims.getAudience();
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

}
