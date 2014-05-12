package com.mobilepetroleum;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    private Lists() {}

    public static <F, T> List<T> transform(List<F> fromList, Function<? super F, ? extends T> function) {
        List<T> toList = Lists.newArrayList();
        for (F f : fromList) {
            toList.add(function.apply(f));
        }
        return toList;
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

}
