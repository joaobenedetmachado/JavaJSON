package org.example;

import com.google.gson.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste your JSON:");

        String inputJson = scanner.useDelimiter("\\A").next();
        scanner.close();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(inputJson, JsonObject.class);

        processJsonObject(jsonObject);
    }

    // a funcao que vai percorrer todos os objetos e listas dentro do JSON
    public static void processJsonObject(JsonObject jsonObject) {
        Iterator<Map.Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonObject()) {
                // se o valor for um objeto/lista e tal, chamamos a função recursivamente
                processJsonObject(value.getAsJsonObject());
            } else if (value.isJsonArray()) {
                System.out.println(key + ": ");
                processJsonArray(value.getAsJsonArray());
            } else {
                System.out.println("    " + key + ": " + value);
            }
        }
    }

    public static void processJsonArray(JsonArray jsonArray) {
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                processJsonObject(element.getAsJsonObject());
            } else {
            }
        }
    }
}