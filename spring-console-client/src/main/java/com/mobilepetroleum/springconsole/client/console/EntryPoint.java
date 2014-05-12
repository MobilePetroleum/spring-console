package com.mobilepetroleum.springconsole.client.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntryPoint {

    private final static Logger logger = LoggerFactory.getLogger("console");

    public static void main(String[] args) {
        try {
            new ConsoleApplication().start(args);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
