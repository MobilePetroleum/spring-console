package com.mobilepetroleum;

class Optional<T> {

    public static Optional EMPTY = of(null);

    private final T value;

    Optional(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }

    T orElse(T other) {
        return isPresent() ? get() : other;
    }

    public boolean isPresent() {
        return value != null;
    }

    public static <T> Optional of(T value) {
        return new Optional<T>(value);
    }

    public static <T> Optional empty() {
        return EMPTY;
    }

}
