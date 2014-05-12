package com.mobilepetroleum.springconsole.client.console;

import com.mobilepetroleum.Optional;

class Cli {

    private final SpringServiceFacade springServiceFacade = SpringServiceFacade.create();

    public void run(String[] args) {

        if (args.length < 1) {
            System.out.println(usage());
            return;
        }

        if ("e".equals(args[0])) {
            springServiceFacade.execute(Optional.of(args, 1).orElse("./execute.json"));
        }

    }

    private String usage() {
        return "Usage:\n"
                + "sco e jsonToExecute.json\n"
                + "sco e (./execute.json by default)"
                ;
    }

}
