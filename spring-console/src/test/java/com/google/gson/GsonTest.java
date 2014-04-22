package com.google.gson;


import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class GsonTest {

    private static final Gson GSON = new Gson();

    @Test
    public void shouldParseString() throws Exception {
        // when
        String data = GSON.fromJson("data", String.class);

        // then
        assertThat(data).isEqualTo("data");
    }

    @Test
    public void shouldParseNumber() throws Exception {
        // when
        int aInt = GSON.fromJson("123", int.class);
        float aFloat = GSON.fromJson("123", float.class);
        double aDouble = GSON.fromJson("123", double.class);
        Number aNumber = GSON.fromJson("123", Number.class);

        // then
        assertThat(aInt).isEqualTo(123);
        assertThat(aFloat).isEqualTo(123);
        assertThat(aDouble).isEqualTo(123);
        assertThat(aNumber.intValue()).isEqualTo(123);
    }


}
