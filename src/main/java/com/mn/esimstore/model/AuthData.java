package com.mn.esimstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthData {
    @JsonProperty("access_token")
    private String token;
}
