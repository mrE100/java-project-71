package hexlet.code;

import hexlet.code.formatter.Stylish;

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
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Map<String, String> value1 = Parser.parse(Files.readString(path1));
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, String> value2 = Parser.parse(Files.readString(path2));
        List<String[]> data = new ArrayList<>();
//        builder.append("{\n");
        Set<String> keys = new HashSet<>();
        keys.addAll(value1.keySet());
        keys.addAll(value2.keySet());
        List<String> sortedKeys = new ArrayList<>(keys);
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            if (value1.containsKey(key) && !value2.containsKey(key)) {
                data.add(new String[]{"deleted", key, String.valueOf(value1.get(key))});
            } else if (value2.containsKey(key) && !value1.containsKey(key)) {
                data.add(new String[]{"added", key, String.valueOf(value2.get(key))});
            } else {
                if (String.valueOf(value1.get(key)).equals(String.valueOf(value2.get(key)))) {
                    data.add(new String[]{"unchanged", key, String.valueOf(value1.get(key))});
                } else {
                    data.add(new String[]{"deleted", key, String.valueOf(value1.get(key))});
                    data.add(new String[]{"added", key, String.valueOf(value2.get(key))});
                }
            }
        }
        if (format.equals("stylish")) {
            return Stylish.format(data);
        } else {
            return null;
        }
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}
