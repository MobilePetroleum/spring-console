package com.mobilepetroleum.springconsole.client.console;

import com.google.gson.Gson;

class GsonStringCommandLoader {

    private final Gson gson = new Gson();

    public SpringCommand create(String json) {
        return gson.fromJson(json, SpringCommand.class);
    }

}
