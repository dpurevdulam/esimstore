package com.mn.esimstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mn.esimstore.model.AuthRequest;
import com.mn.esimstore.model.AuthResponse;

@Service
public class EsimStoreService {

    @Autowired
    RestTemplate restTemplate;

    private final String url = "https://partners-api.airalo.com/v1/token";
    private final String clientId = "108cbadd1d6489979ff9fbe49dc016ef";
    private final String clientSecret = "0IL3jWOgVPiabqajd8e9EYPKS7dMQOczQJZm0wjA";

    public Object getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        AuthRequest authRequest = new AuthRequest();
        authRequest.setClientId(clientId);
        authRequest.setClientSecret(clientSecret);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(url, requestEntity,
                AuthResponse.class);
        return responseEntity.getBody();
    }
}
