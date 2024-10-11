package com.mn.esimstore.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String clientId;
    private String clientSecret;
}
