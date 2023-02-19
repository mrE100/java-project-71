package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Map<String, String> value1 = Parser.parse(Files.readString(path1));
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, String> value2 = Parser.parse(Files.readString(path2));
        StringBuilder builder = new StringBuilder();
        builder.append("{\r\n");
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
                    builder.append("   - ").append(key).append(": ")
                            .append(String.valueOf(value1.get(key))).append("\r\n");
                    operation = "   + ";
                    keyValue = value2.get(key);
                }
            }
            builder.append(operation).append(key).append(": ").append(keyValue).append("\r\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
