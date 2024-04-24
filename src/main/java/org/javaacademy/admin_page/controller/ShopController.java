package org.javaacademy.admin_page.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.admin_page.dto.PriceDto;
import org.javaacademy.admin_page.dto.UserDto;
import org.javaacademy.admin_page.repository.AuthRepository;
import org.javaacademy.admin_page.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coffeeshop/price")
@RequiredArgsConstructor
public class ShopController {
    private final AuthRepository authRepository;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<?> getAllPrices(@RequestHeader("token") String token) {
        UserDto userDto = tokenService.deserializeToken(token);
        if (!authRepository.login(userDto.getLogin(), userDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(List.of(
                new PriceDto("Латте", 300),
                new PriceDto("Капучино", 200)
        ));
    }
}
