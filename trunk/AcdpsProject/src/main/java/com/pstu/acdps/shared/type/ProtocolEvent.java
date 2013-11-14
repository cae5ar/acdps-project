package com.pstu.acdps.shared.type;

public class ProtocolEvent {
    public static final String UNKNOWN = "UNKNOWN";
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";

    public static String getText(String string) {
        if (string == null) return "";
        if (UNKNOWN.equalsIgnoreCase(string)) return "Неизвестно";

        if (LOGIN.equalsIgnoreCase(string)) return "Авторизация";
        if (LOGOUT.equalsIgnoreCase(string)) return "Выход";
        return "";
    }
}
