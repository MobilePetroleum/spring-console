package com.mobilepetroleum.springconsole.client.console;

import com.google.gson.Gson;
import com.mobilepetroleum.Closeables;

import java.io.*;

class GsonFileCommandLoader {

    private final Gson gson = new Gson();

    @SuppressWarnings("ThrowFromFinallyBlock")
    public SpringCommand create(String path) {
        return readFromPath(path);
    }

    SpringCommand readFromPath(String path) {
        SpringCommand springCommand;

        FileReader fileReader = null;
        boolean threw = true;
        try {
            fileReader = new FileReader(path);
            springCommand = gson.fromJson(fileReader, SpringCommand.class);
            threw = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Closeables.close(fileReader, threw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return springCommand;
    }

}
