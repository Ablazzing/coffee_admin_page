package org.javaacademy.admin_page.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthRepository {
    private Map<String, String> map = new HashMap<>();

    /**
     * Создание пользователя
     */
    public void createUser(String login, String password) {
        if (map.containsKey(login)) {
            throw new RuntimeException("User is already exists");
        }
        map.put(login, password);
    }

    /**
     * Корректные ли данные
     */
    public boolean login(String login, String password) {
        return Objects.equals(password, map.get(login));
    }

}
