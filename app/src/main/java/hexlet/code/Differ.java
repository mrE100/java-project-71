package hexlet.code;

import hexlet.code.formatter.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Map<String, String> value1 = Parser.parse(Files.readString(path1));
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        Map<String, String> value2 = Parser.parse(Files.readString(path2));
        TreeMap<String, LinkedHashMap<String, String>> data = new TreeMap<>();
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(value1.keySet());
        keys.addAll(value2.keySet());
        for (String key : keys) {
            LinkedHashMap<String, String> keyData = new LinkedHashMap<>();
            if (value1.containsKey(key) && !value2.containsKey(key)) {
                keyData.put("type", "deleted");
                keyData.put("value", String.valueOf(value1.get(key)));
            } else if (value2.containsKey(key) && !value1.containsKey(key)) {
                keyData.put("type", "added");
                keyData.put("value", String.valueOf(value2.get(key)));
            } else {
                if (String.valueOf(value1.get(key)).equals(String.valueOf(value2.get(key)))) {
                    keyData.put("type", "unchanged");
                    keyData.put("value", String.valueOf(value1.get(key)));
                } else {
                    keyData.put("type", "changed");
                    keyData.put("value1", String.valueOf(value1.get(key)));
                    keyData.put("value2", String.valueOf(value2.get(key)));
                }
            }
            data.put(key, keyData);
        }
        return Formatter.getOutputText(data, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}
