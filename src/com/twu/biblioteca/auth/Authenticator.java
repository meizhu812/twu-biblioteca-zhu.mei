package com.twu.biblioteca.auth;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Authenticator {
    private final Map<String, User> users;
    private final Map<String, UserInfo> userInfo;

    public Authenticator(Collection<User> users, Collection<UserInfo> userInfo) {
        this.users = users.stream()
                .collect(Collectors.toMap(
                        User::getCardNo, Function.identity(), (a, b) -> a));
        this.userInfo = userInfo.stream()
                .collect(Collectors.toMap(
                        UserInfo::getCardNo, Function.identity(), (a, b) -> a));
    }

    public User authenticate(LoginInput loginInput) throws InvalidCredential {
        return Optional.ofNullable(users.getOrDefault(loginInput.getCardNo(), null))
                .filter(user -> user.getPassword().equals(loginInput.getPassword()))
                .orElseThrow(InvalidCredential::new);
    }

    public UserInfo getUserInfoByUser(User user) {
        return userInfo.get(user.getCardNo());
    }
}
