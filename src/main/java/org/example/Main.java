package org.example;

import com.google.gson.*;
import org.example.colors.Colors;

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
                processJsonObject(value.getAsJsonObject(), "");
            } else if (value.isJsonArray()) {
                System.out.println(Colors.RED + key + ": " + Colors.RESET);
            processJsonArray(value.getAsJsonArray(), "");
            } else {
                System.out.println("    " + Colors.BLUE_BOLD + key + ": " + Colors.BLUE +value);
            }
        }
    }

    // Passe um parâmetro extra para rastrear o caminho atual
    public static void processJsonObject(JsonObject jsonObject, String path) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            String fullPath = path.isEmpty() ? key : path + "." + key;

            if (value.isJsonObject()) {
                processJsonObject(value.getAsJsonObject(), fullPath);
            } else if (value.isJsonArray()) {
                System.out.println(Colors.RED + fullPath + ": " + Colors.RESET);
                processJsonArray(value.getAsJsonArray(), fullPath);
            } else {
                System.out.println("    " + Colors.BLUE_BOLD + fullPath + ": " + Colors.BLUE + value);
            }
        }
    }

    public static void processJsonArray(JsonArray jsonArray, String path) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement element = jsonArray.get(i);
            String newPath = path + "[" + i + "]";
            if (element.isJsonObject()) {
                processJsonObject(element.getAsJsonObject(), newPath);
            } else {
                System.out.println("    " + Colors.PURPLE + newPath + ": " + Colors.RESET + element);
            }
        }
    }}
