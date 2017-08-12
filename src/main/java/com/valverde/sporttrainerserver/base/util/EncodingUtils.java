package com.valverde.sporttrainerserver.base.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodingUtils {

    public static String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
