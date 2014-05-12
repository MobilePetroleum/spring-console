package com.mobilepetroleum.springconsole.client.console;

public class SpringServiceFacade {

    private final GsonFileCommandLoader gsonFileCommandLoader = new GsonFileCommandLoader();
    private final GsonStringCommandLoader gsonStringCommandLoader = new GsonStringCommandLoader();
    private final SpringCommandRunner springCommandRunner = new SpringCommandRunner();

    private SpringServiceFacade() {}

    public void execute(String path) {
        springCommandRunner.run(gsonFileCommandLoader.create(path));
    }

    public void executeJson(String json) {
        springCommandRunner.run(gsonStringCommandLoader.create(json));
    }

    public static SpringServiceFacade create() {
        return new SpringServiceFacade();
    }
}
