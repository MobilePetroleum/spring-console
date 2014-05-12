package com.mobilepetroleum;

import com.google.gson.Gson;

class ResultToStringConverter {

    Gson gson = new Gson();

    String toString(Object o) {

        String message = "Could not serialize to json, reason = ";

        try {
            return gson.toJson(o);
        } catch (Exception e) {
            message += e.getMessage() + ".";
        } catch (StackOverflowError e) {
            message += e.getMessage() + ".";
        }

        try {
            String toStringResult = String.valueOf(o);
            message += " String.valueOf(result) = ";
            return message + toStringResult;
        } catch (Exception e) {
            message += " String.valueOf(result) also failed, reason = " + e.getMessage() + ".";
        } catch (StackOverflowError e) {
            message += " String.valueOf(result) also failed, reason = " + e.getMessage() + ".";
        }

        return message;
    }

}
