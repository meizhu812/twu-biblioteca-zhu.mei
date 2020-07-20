package com.twu.biblioteca;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    public Map<String, Option<?>> getOptions() {
        return options;
    }

    private final Map<String, Option<?>> options;

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private Scanner scanner;

    public Menu(Collection<Option<?>> options, Scanner scanner) {
        this.options = options.stream()
                .collect(Collectors.toMap(Option::getSerial, Function.identity(), (a, b) -> b));
        setScanner(scanner);
    }

    void runOption(String input) throws InvalidOption {
        Option<?> option = Optional.ofNullable(options.getOrDefault(input, null))
                .orElseThrow(InvalidOption::new);
        Operation<?> operation = option.getOperation();
        String inputPrompt = option.getInputPrompt();
        if (inputPrompt != null) {
            System.out.println(inputPrompt);
            operation.consoleExecute(scanner.nextLine());
        } else {
            operation.consoleExecute();
        }
    }
}

