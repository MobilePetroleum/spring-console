package com.mobilepetroleum;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.Reader;
import java.lang.reflect.Type;

@SuppressWarnings("UnusedDeclaration")
class Gson {
    com.google.gson.Gson gson = new com.google.gson.Gson();

    public Gson(com.google.gson.Gson gson) { this.gson = gson; }

    public Gson() { }

    public <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
        return gson.getAdapter(type);
    }

    public <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }

    public String toJson(Object src) {
        return gson.toJson(src);
    }

    public String toJson(JsonElement jsonElement) {
        return gson.toJson(jsonElement);
    }

    public void toJson(Object src, Appendable writer) throws JsonIOException {
        gson.toJson(src, writer);
    }

    public void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
        gson.toJson(jsonElement, writer);
    }

    public JsonElement toJsonTree(Object src, Type typeOfSrc) {
        return gson.toJsonTree(src, typeOfSrc);
    }

    public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        gson.toJson(jsonElement, writer);
    }

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
        gson.toJson(src, typeOfSrc, writer);
    }

    public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }

    public <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }

    public <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return gson.fromJson(reader, typeOfT);
    }

    public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        return gson.getDelegateAdapter(skipPast, type);
    }

    public <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return gson.fromJson(json, classOfT);
    }

    public JsonElement toJsonTree(Object src) {
        return gson.toJsonTree(src);
    }

    public void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
        gson.toJson(src, typeOfSrc, writer);
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return gson.getAdapter(type);
    }

    public String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }
}
