package com.twu.biblioteca.auth;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Authenticator {
    private final Map<String, User> users;

    public Authenticator(Collection<User> users) {
        this.users = users.stream()
                .collect(Collectors.toMap(
                        User::getCardNo, Function.identity(), (a, b) -> a));
    }

    public User authenticate(LoginInput loginInput) throws InvalidCredential {
        return Optional.ofNullable(users.getOrDefault(loginInput.getCardNo(), null))
                .filter(user -> user.getPassword().equals(loginInput.getPassword()))
                .orElseThrow(InvalidCredential::new);
    }
}
