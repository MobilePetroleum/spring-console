package com.mobilepetroleum;


import java.io.Serializable;

public class MethodParameter implements Serializable {

    private String type = "";
    private String value = "";

    public MethodParameter(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() { return type; }

    public String getValue() { return value; }

}
