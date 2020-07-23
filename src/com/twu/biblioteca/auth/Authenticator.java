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

    public Optional<User> authenticate(LoginInput loginInput) {
        Optional<User> optionalUser = Optional.ofNullable(users.getOrDefault(loginInput.getCardNo(), null));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(loginInput.getPassword())) {
                return optionalUser;
            }
        }
        return Optional.empty();
    }
}
