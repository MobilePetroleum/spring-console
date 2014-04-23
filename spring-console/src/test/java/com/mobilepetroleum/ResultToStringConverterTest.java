package com.mobilepetroleum;

import org.junit.Test;
import org.mockito.Mockito;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;

public class ResultToStringConverterTest {

    @Test
    public void shouldProduceJson() throws Exception {
        // given
        ResultToStringConverter resultToStringConverter = new ResultToStringConverter();
        // when
        String s = resultToStringConverter.toString(new Object());
        // then
        assertThat(s).isEqualTo("{}");
    }

    @Test
    public void shouldProduceToString() throws Exception {
        // given
        ResultToStringConverter resultToStringConverter = new ResultToStringConverter();
        resultToStringConverter.gson = Mockito.mock(Gson.class);
        given(resultToStringConverter.gson.toJson(anyObject())).willThrow(new RuntimeException());

        // when
        String s = resultToStringConverter.toString(new Object() {
            @Override public String toString() { return "toString"; }
        });

        // then
        assertThat(s).isEqualTo("Could not serialize to json, reason = null. String.valueOf(result) = toString");
    }

    @Test
    public void shouldProduceErrorMessage() throws Exception {
        // given
        ResultToStringConverter resultToStringConverter = new ResultToStringConverter();
        resultToStringConverter.gson = Mockito.mock(Gson.class);
        given(resultToStringConverter.gson.toJson(anyObject())).willThrow(new RuntimeException());

        // when
        String s = resultToStringConverter.toString(new Object() {
            @Override public String toString() { throw new RuntimeException(); }
        });

        // then
        assertThat(s).isEqualTo("Could not serialize to json, reason = null. String.valueOf(result) also failed, reason = null.");
    }


}