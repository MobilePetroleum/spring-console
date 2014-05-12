package com.mobilepetroleum.springconsole.client.console;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

class Invocation {

    private String beanName = null;
    private String method = null;
    private String[] types = new String[]{};
    private JsonElement args = JsonNull.INSTANCE;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public JsonElement getArgs() {
        return args;
    }

    public void setArgs(JsonElement args) {
        this.args = args;
    }

}
