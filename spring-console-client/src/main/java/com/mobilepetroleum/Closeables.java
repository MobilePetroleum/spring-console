package com.mobilepetroleum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;

public class Closeables {

    private static final Logger logger = LoggerFactory.getLogger("console");

    private Closeables() {}

    public static void close(Closeable closeable, boolean swallowIOException) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            if (swallowIOException) {
                logger.warn("IOException thrown while closing Closeable.", e);
            } else {
                throw e;
            }
        }
    }
}
