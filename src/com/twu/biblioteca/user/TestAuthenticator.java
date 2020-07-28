package com.twu.biblioteca.user;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestAuthenticator implements Authenticator {
    private final Map<String, User> users;


    public TestAuthenticator(Collection<User> users) {
        this.users = users.stream()
                .collect(Collectors.toMap(
                        User::getCardNo, Function.identity(), (a, b) -> a));

    }

    @Override
    public User authenticate(LoginInput loginInput) throws InvalidCredential {
        Predicate<User> hasSamePassword = user -> Objects.equals(loginInput.getPassword(), user.getPassword());
        return Optional.ofNullable(users.getOrDefault(loginInput.getCardNo(), null))
                .filter(hasSamePassword)
                .orElseThrow(InvalidCredential::new);
    }

}
