package com.twu.biblioteca.console;

public class Option<R> {
    private final String serial;
    private final String description;
    private final String inputPrompt;
    private final Operation<R> operation;

    public Option(String serial, String description, String inputPrompt, Operation<R> operation) {
        this.serial = serial;
        this.description = description;
        this.inputPrompt = inputPrompt;
        this.operation = operation;
    }

    public String getSerial() {
        return serial;
    }

    public String getDescription() {
        return description;
    }

    public String getInputPrompt() {
        return inputPrompt;
    }

    public Operation<R> getOperation() {
        return operation;
    }
}
