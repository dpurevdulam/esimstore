package com.mn.esimstore.security.jwt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mn.esimstore.component.EsimStoreProperties;
import com.mn.esimstore.security.exception.ErrorType;
import com.mn.esimstore.security.exception.ServiceException;
import com.mn.esimstore.security.helper.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    @Autowired
    private EsimStoreProperties esimStoreProperties;

    // private String application = "esimstore";
    // private String secret = "secret";
    private int expiration = 60000;

    public String generate(String audience) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", audience);
        claims.put("aud", audience);
        return this.generate(claims);
    }

    public String generate(Map<String, Object> claims) {
        try {
            return Jwts.builder().setClaims(claims).setIssuer(esimStoreProperties.getApplication())
                    .setIssuedAt(Utils.getCurrentDate())
                    .setExpiration(Utils.getExpirationDate(expiration))
                    .signWith(signatureAlgorithm, esimStoreProperties.getJwtSecret().getBytes("UTF-8")).compact();
        } catch (UnsupportedEncodingException ex) {
            throw new ServiceException(ErrorType.SERVICE_ERROR, ex.getMessage());
        }
    }
}
