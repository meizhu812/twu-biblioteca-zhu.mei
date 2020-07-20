package com.twu.biblioteca.console;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String, Option<?>> options;
    private Scanner scanner;

    public Menu(Collection<Option<?>> options, Scanner scanner) {
        this.options = options.stream().collect(
                Collectors.toMap(Option::getSerial, Function.identity(), (a, b) -> b));
        setScanner(scanner);
    }

    public void prompt() {
        System.out.println("Please select an option:");
        options.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.printf("%2s - %s\n", entry.getKey(), entry.getValue().getDescription()));
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void runOption(String input) throws InvalidOption {
        Option<?> option = Optional.ofNullable(options.getOrDefault(input, null))
                .orElseThrow(InvalidOption::new);
        Operation<?> operation = option.getOperation();
        String inputPrompt = option.getInputPrompt();
        if (inputPrompt != null) {
            System.out.println(inputPrompt);
            operation.consoleExecute(scanner.next());
        } else {
            operation.consoleExecute();
        }
    }
}

