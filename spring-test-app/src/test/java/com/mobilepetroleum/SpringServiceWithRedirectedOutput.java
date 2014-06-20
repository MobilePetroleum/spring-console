package com.mobilepetroleum;

import com.google.common.io.Closeables;
import com.mobilepetroleum.springconsole.client.console.SpringServiceFacade;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class SpringServiceWithRedirectedOutput {

    @SuppressWarnings("ThrowFromFinallyBlock")
    public String executeJson(String json) {

        PrintStream out = System.out;
        PrintStream err = System.err;

        boolean threw = true;
        ByteArrayOutputStream interceptedStream = new ByteArrayOutputStream();
        try {

            System.setOut(new PrintStream(interceptedStream));
            System.setErr(new PrintStream(interceptedStream));

            SpringServiceFacade.create().executeJson(json);

            threw = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(out);
            System.setErr(err);
            try {
                Closeables.close(interceptedStream, threw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return interceptedStream.toString();
    }

}
