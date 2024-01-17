package com.brainwired.User.Utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class UserUtils {

    public static String generateUserId(){
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return "BW" + randomString.toString();
    }
}
