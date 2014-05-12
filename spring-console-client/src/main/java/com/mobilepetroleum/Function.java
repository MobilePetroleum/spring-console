package com.mobilepetroleum;

public interface Function<F, T> {

    T apply(@Nullable F input);

}
