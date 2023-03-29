package hexlet.code;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Objects;

public class DiffBuilder {

    public static Map generateDiff(Map<String, Object> value1, Map<String, Object> value2) {
        Map data = new TreeMap<>();
        Set keys = new TreeSet<>();
        keys.addAll(value1.keySet());
        keys.addAll(value2.keySet());
        for (var key : keys) {
            Map keyData = new LinkedHashMap<>(4);
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
                if (Objects.equals(value1value, value2value)) {
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
