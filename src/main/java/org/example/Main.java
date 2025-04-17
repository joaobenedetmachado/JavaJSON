package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.models.Tasks;
import org.example.models.User;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste your JSON:");

        String inputJson = scanner.useDelimiter("\\A").next();
        scanner.close();

        Gson gson = new Gson();

        Type userListType = new TypeToken<List<User>>(){}.getType();

        List<User> users = gson.fromJson(inputJson, userListType);

        for (User u : users) {
            System.out.println("User: " + u.getName());
            for (Tasks t : u.getTasks()) {
                String status = t.isDone() ? "CONCLUDED" : "PENDING";
                System.out.println("  - " + t.getTitle() + " [" + status + "]");
            }
        }
    }
}
