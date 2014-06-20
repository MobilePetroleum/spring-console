package com.mobilepetroleum;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SpringConsoleTest extends JunitSpringTest {

    @Test
    public void shouldProduceExpectedOutput() throws Exception {
        // given
        String jsonToExecute = Resources.toString(Resources.getResource("execute.json"), Charsets.UTF_8);

        // when
        String result = new SpringServiceWithRedirectedOutput().executeJson(jsonToExecute);

        // then
        String expected = Resources.toString(Resources.getResource("expected_text_in_console.txt"), Charsets.UTF_8);
        assertThat(result).isEqualTo(expected);
    }

}
