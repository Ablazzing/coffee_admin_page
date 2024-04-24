package org.javaacademy.admin_page.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.admin_page.dto.UserDto;
import org.javaacademy.admin_page.repository.AuthRepository;
import org.javaacademy.admin_page.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/coffeeshop/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthRepository authRepository;
    private final TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        authRepository.createUser(userDto.getLogin(), userDto.getPassword());
        return status(CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {
        if (authRepository.login(userDto.getLogin(), userDto.getPassword())) {
            return accepted().body(tokenService.createToken(userDto));
        }
        return status(FORBIDDEN).body("Некорректные данные: логин или пароль неверные");
    }
}
