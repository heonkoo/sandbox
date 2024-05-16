package com.hlee.sandbox.rest.forismatic;

public enum Language {
    ENGLISH("en"),
    RUSSIAN("ru");

    private final String value;

    private Language(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
