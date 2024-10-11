package com.mn.esimstore.security.validator;

import com.mn.esimstore.security.user.SecurityUser;

public interface TokenValidator {
    String getPrefix();

    SecurityUser validate(String token, String secret);
}
