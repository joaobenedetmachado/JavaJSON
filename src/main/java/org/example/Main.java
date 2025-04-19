package org.example;

import com.google.gson.*;
import org.example.colors.Colors;
import org.example.models.JsonStats;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste your JSON:");
        String inputJson = scanner.useDelimiter("\\A").next();
        scanner.close();

        Gson gson = new Gson();

        try {
            JsonObject jsonObject = gson.fromJson(inputJson, JsonObject.class);
            JsonStats stats = new JsonStats();
            processJsonObject(jsonObject, "", 1, stats);
            stats.printStats();
        } catch (JsonSyntaxException e) {
            System.out.println(Colors.RED + "JSON inválido!" + Colors.RESET);
        }
    }

    public static void processJsonObject(JsonObject jsonObject, String path, int depth, JsonStats stats) {
        stats.totalObjects++;
        stats.maxDepth = Math.max(stats.maxDepth, depth);

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            stats.totalKeys++;
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            String fullPath = path.isEmpty() ? key : path + "." + key;

            if (value.isJsonObject()) {
                processJsonObject(value.getAsJsonObject(), fullPath, depth + 1, stats);
            } else if (value.isJsonArray()) {
                stats.totalArrays++;
                System.out.println(Colors.RED + fullPath + ": [Array]" + Colors.RESET);
                processJsonArray(value.getAsJsonArray(), fullPath, depth + 1, stats);
            } else if (value.isJsonNull()) {
                stats.totalNulls++;
                System.out.println("    " + Colors.GRAY + fullPath + ": null" + Colors.RESET);
            } else {
                printPrimitive(fullPath, value.getAsJsonPrimitive(), stats);
            }
        }
    }

    public static void processJsonArray(JsonArray jsonArray, String path, int depth, JsonStats stats) {
        stats.maxDepth = Math.max(stats.maxDepth, depth);
        int index = 0;

        for (JsonElement element : jsonArray) {
            String currentPath = path + "[" + index + "]";
            if (element.isJsonObject()) {
                processJsonObject(element.getAsJsonObject(), currentPath, depth + 1, stats);
            } else if (element.isJsonArray()) {
                stats.totalArrays++;
                System.out.println(Colors.RED + currentPath + ": [Nested Array]" + Colors.RESET);
                processJsonArray(element.getAsJsonArray(), currentPath, depth + 1, stats);
            } else if (element.isJsonNull()) {
                stats.totalNulls++;
                System.out.println("    " + Colors.GRAY + currentPath + ": null" + Colors.RESET);
            } else {
                printPrimitive(currentPath, element.getAsJsonPrimitive(), stats);
            }
            index++;
        }
    }

    public static void printPrimitive(String path, JsonPrimitive primitive, JsonStats stats) {
        if (primitive.isString()) {
            stats.totalStrings++;
            System.out.println("    " + Colors.BLUE_BOLD + path + ": " + Colors.BLUE + primitive.getAsString() + Colors.RESET);
        } else if (primitive.isNumber()) {
            stats.totalNumbers++;
            System.out.println("    " + Colors.GREEN_BOLD + path + ": " + Colors.GREEN + primitive.getAsNumber() + Colors.RESET);
        } else if (primitive.isBoolean()) {
            stats.totalBooleans++;
            System.out.println("    " + Colors.YELLOW + path + ": " + primitive.getAsBoolean() + Colors.RESET);
        }
    }
}
