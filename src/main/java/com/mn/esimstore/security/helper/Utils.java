package com.mn.esimstore.security.helper;

import java.io.IOException;
import java.util.Date;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mn.esimstore.security.exception.ErrorType;

import jakarta.servlet.http.HttpServletResponse;

public final class Utils {
    private Utils() {
        throw new IllegalStateException("Utils class");
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getExpirationDate(int expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static void sendResponse(HttpServletResponse response, ErrorType errorType)
            throws JsonProcessingException, IOException {
        response.setStatus(errorType.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(toJson(errorType));
    }
}
