package org.javaacademy.admin_page.service;

import org.javaacademy.admin_page.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static final String PREFIX = "token";
    private static final String POSTFIX = "pass";

    public String createToken(UserDto userDto) {
        return PREFIX + userDto.getLogin() + POSTFIX + userDto.getPassword();
    }

    public UserDto deserializeToken(String token) {
        String data = token.trim().substring(PREFIX.length());
        int finalUserNameIndex = data.indexOf(POSTFIX);
        String login = data.substring(0, finalUserNameIndex);
        String password = data.substring(finalUserNameIndex + POSTFIX.length());
        return new UserDto(login, password);
    }
}
