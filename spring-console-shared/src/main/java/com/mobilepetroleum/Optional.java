package com.mobilepetroleum;

@SuppressWarnings("unchecked")
public class Optional<T> {

    public static Optional EMPTY = of(null);

    private final T value;

    Optional(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public T orElse(T other) {
        return isPresent() ? get() : other;
    }

    public boolean isPresent() {
        return value != null;
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<T>(value);
    }

    public static <T> Optional<T> of(T[] array, int index) {
        return index < array.length ? new Optional<T>(array[index]) : EMPTY;
    }

    public static <T> Optional<T> empty() {
        return EMPTY;
    }

}
