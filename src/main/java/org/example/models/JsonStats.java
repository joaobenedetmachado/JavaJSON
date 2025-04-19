package org.example.models;

public class JsonStats {
    public int totalKeys = 0;
    public int totalObjects = 0;
    public int totalArrays = 0;
    public int totalStrings = 0;
    public int totalNumbers = 0;
    public int totalBooleans = 0;
    public int totalNulls = 0;
    public int maxDepth = 0;

    public void printStats() {
        System.out.println("\n====== JSON STATS ======");
        System.out.println("Total Keys      : " + totalKeys);
        System.out.println("Objects         : " + totalObjects);
        System.out.println("Arrays          : " + totalArrays);
        System.out.println("Strings         : " + totalStrings);
        System.out.println("Numbers         : " + totalNumbers);
        System.out.println("Booleans        : " + totalBooleans);
        System.out.println("Nulls           : " + totalNulls);
        System.out.println("Max Depth       : " + maxDepth);
    }
}
