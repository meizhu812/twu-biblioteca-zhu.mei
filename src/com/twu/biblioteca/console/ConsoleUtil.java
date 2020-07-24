package com.twu.biblioteca.console;

import java.lang.reflect.Method;
import java.util.Map;

public final class ConsoleUtil {
    private ConsoleUtil() {
    }

    public static Option methodToOption(Method method) {
        MenuItem annotation = getMenuItemAnno(method);
        return new Option(annotation.serial(), annotation.desc(), annotation.prompt(), method);
    }

    public static MenuItem getMenuItemAnno(Method method) {
        return method.getAnnotation(MenuItem.class);
    }

    public static String getOptionsPrompt(Map<String, Option> options) {
        StringBuffer stringBuffer = new StringBuffer("Please select an option:\n");
        options.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> stringBuffer.append(String.format("%2s - %s\n", entry.getKey(), entry.getValue().getDescription())));
        return stringBuffer.toString();
    }
}
