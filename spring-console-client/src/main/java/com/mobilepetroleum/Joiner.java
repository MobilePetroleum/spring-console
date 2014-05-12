package com.mobilepetroleum;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class Joiner {

    private final String separator;

    private Joiner(String separator) {this.separator = separator;}

    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    public String join(Object... o) {
        return join(Arrays.asList(o).iterator());
    }

    public String join(Iterator<?> iterator) {
        return appendTo(new StringBuilder(), iterator).toString();
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) {
        try {
            if (parts.hasNext()) {
                appendable.append(toString(parts.next()));
                while (parts.hasNext()) {
                    appendable.append(separator);
                    appendable.append(toString(parts.next()));
                }
            }
            return appendable;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CharSequence toString(Object part) {
        return String.valueOf(part);
    }

}
