package io.hexlet.app;

import java.util.*;

public class Differ {
    public static String generate(Map<String, String> value1, Map<String, String> value2) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        Set<String> keys = new HashSet<>();
        keys.addAll(value1.keySet());
        keys.addAll((value2.keySet()));
        List<String> sortedKeys = new ArrayList<>(keys);
        Collections.sort(sortedKeys);
        String operation;
        Object keyValue;
        for (String key : sortedKeys) {
            if (value1.containsKey(key) && !value2.containsKey(key)) {
                operation = "   - ";
                keyValue = value1.get(key);
            } else if (value2.containsKey(key) && !value1.containsKey(key)) {
                operation = "   + ";
                keyValue = value2.get(key);
            } else {
                if (String.valueOf(value1.get(key)).equals(String.valueOf(value2.get(key)))) {
                    operation = "     ";
                    keyValue = value1.get(key);
                } else {
                    builder.append("   - ").append(key).append(": ").append(String.valueOf(value1.get(key))).append("\n");
                    operation = "   + ";
                    keyValue = value2.get(key);
                }
            }
            builder.append(operation).append(key).append(": ").append(keyValue).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
