package com.twu.biblioteca;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    public Map<String, Option<?>> getOptions() {
        return options;
    }

    private final Map<String, Option<?>> options;

    public Menu(Collection<Option<?>> options) {
        this.options = options.stream()
                .collect(Collectors.toMap(Option::getSerial, Function.identity(), (a, b) -> b));
    }

    void runOption(String input) throws InvalidOption {
        Optional.ofNullable(options.getOrDefault(input, null))
                .orElseThrow(InvalidOption::new)
                .getOperation()
                .consoleExecute();
    }
}

