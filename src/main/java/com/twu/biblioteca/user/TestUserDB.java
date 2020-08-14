package com.twu.biblioteca.user;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestUserDB implements UserDB {
    private final Map<String, UserInfo> userInfo;

    public TestUserDB(Collection<UserInfo> userInfo) {
        this.userInfo = userInfo.stream()
                .collect(Collectors.toMap(
                        UserInfo::getCardNo, Function.identity(), (a, b) -> a));
    }

    @Override
    public UserInfo getUserInfoByUser(User user) {
        return userInfo.get(user.getCardNo());
    }
}
