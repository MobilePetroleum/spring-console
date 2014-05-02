package com.mobilepetroleum.test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.fest.assertions.api.Assertions.assertThat;

public class TestRunner {

    private final String sco;
    private final String jsonToExecute;

    TestRunner(String sco, String jsonToExecute) {
        this.sco = sco;
        this.jsonToExecute = jsonToExecute;
    }

    public void execute() {
        try {
            InputStream inputStream = createProcess();

            String input = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));

            String expectedInput = Resources.toString(Resources.getResource("expected_input.txt"), Charsets.UTF_8);

            assertThat(input).isEqualTo(expectedInput);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream createProcess() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(sco, "e", jsonToExecute);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        return process.getInputStream();
    }

}
