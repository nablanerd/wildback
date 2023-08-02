package com.templateproject.api.service;

import java.security.SecureRandom;
import java.util.Arrays;

public class GenerateToken {

    public static String newUserToken(int userID) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Arrays.toString(bytes) + userID;
    }
}
