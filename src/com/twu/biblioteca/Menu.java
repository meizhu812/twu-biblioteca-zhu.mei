package com.twu.biblioteca;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String, Option<?>> options;

    public Menu(Collection<Option<?>> options) {
        this.options = options.stream()
                .collect(Collectors.toMap(Option::getSerial, Function.identity(), (a, b) -> b));
    }

    void runOption(String input) {
        Optional.ofNullable(options.getOrDefault(input, null))
                .get()
                .getOperation()
                .consoleExecute();
    }
}

