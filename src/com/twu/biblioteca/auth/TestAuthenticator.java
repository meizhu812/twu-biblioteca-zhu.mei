package com.twu.biblioteca.auth;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestAuthenticator implements Authenticator {
    private final Map<String, User> users;
    private final Map<String, UserInfo> userInfo;

    public TestAuthenticator(Collection<User> users, Collection<UserInfo> userInfo) {
        this.users = users.stream()
                .collect(Collectors.toMap(
                        User::getCardNo, Function.identity(), (a, b) -> a));
        this.userInfo = userInfo.stream()
                .collect(Collectors.toMap(
                        UserInfo::getCardNo, Function.identity(), (a, b) -> a));
    }

    @Override
    public User authenticate(LoginInput loginInput) throws InvalidCredential {
        Predicate<User> hasSamePassword = user -> Objects.equals(loginInput.getPassword(), user.getPassword());
        return Optional.ofNullable(users.getOrDefault(loginInput.getCardNo(), null))
                .filter(hasSamePassword)
                .orElseThrow(InvalidCredential::new);
    }

    @Override
    public UserInfo getUserInfoByUser(User user) {
        return userInfo.get(user.getCardNo());
    }
}
