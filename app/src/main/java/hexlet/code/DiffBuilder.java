package hexlet.code;

import java.util.*;

public class DiffBuilder {

    public static TreeMap generateDiff(Map<String, Object> value1, Map<String, Object> value2){
        TreeMap<String, LinkedHashMap<String, Object>> data = new TreeMap<>();
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(value1.keySet());
        keys.addAll(value2.keySet());
        for (String key : keys) {
            LinkedHashMap<String, Object> keyData = new LinkedHashMap<>(4);
            // используем LinkedHashMap чтобы сохранить последовательность добавления значений - требуется для
            // форматтера Json
            if (value1.containsKey(key) && !value2.containsKey(key)) {
                keyData.put("type", "deleted");
                keyData.put("value", value1.get(key));
            } else if (value2.containsKey(key) && !value1.containsKey(key)) {
                keyData.put("type", "added");
                keyData.put("value", value2.get(key));
            } else {
                Object value1value = value1.get(key);
                Object value2value = value2.get(key);
                if (value1value == null) {
                    value1value = "null";
                }
                if (value2value == null) {
                    value2value = "null";
                }
                if (value1value.equals(value2value)) {
                    keyData.put("type", "unchanged");
                    keyData.put("value", value1value);
                } else {
                    keyData.put("type", "changed");
                    keyData.put("value1", value1value);
                    keyData.put("value2", value2value);
                }
            }
            data.put(key, keyData);
        }
        return data;
    }
}
