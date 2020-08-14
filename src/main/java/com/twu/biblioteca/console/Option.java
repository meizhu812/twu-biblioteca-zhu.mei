package com.twu.biblioteca.console;

import java.lang.reflect.Method;

public class Option {
    private final String serial;
    private final String description;
    private final String inputPrompt;
    private final Method method;

    public Option(String serial, String description, String inputPrompt, Method method) {
        this.serial = serial;
        this.description = description;
        this.inputPrompt = inputPrompt;
        this.method = method;
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

    public Method getMethod() {
        return method;
    }
}
