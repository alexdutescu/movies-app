package com.jfsd.moviebooking.enums;

import java.util.stream.Stream;

public enum MovieType {

    COMEDY("comedy"),
    ACTION("action"),
    DRAMA("drama"),
    ROMANCE("romance"),
    THRILLER("thriller");

    private final String type;

    MovieType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static MovieType of(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Stream.of(MovieType.values())
                .filter(c -> c.getType().equals(value))
                .findFirst()
                .orElse(null);
    }
}
