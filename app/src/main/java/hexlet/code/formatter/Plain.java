package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(Map<String, Map<String, String>> data) {
        StringBuilder builder = new StringBuilder();
        String keyValue;
        for (String key : data.keySet()) {
            Map<String, String> keyData = data.get(key);
            String operand = keyData.get("type");
            keyValue = valueToString(keyData.get("value"));
            switch (operand) {
                case "deleted":
//                    builder.append(String.format("Property '%s' was removed\r\n", key));
                    builder.append(String.format("Property '%s' was removed\n", key));
                    break;
                case "added":
//                    builder.append(String.format("Property '%s' was added with value: %s\r\n", key, keyValue));
                    builder.append(String.format("Property '%s' was added with value: %s\n", key, keyValue));
                    break;
                case "unchanged":
                    break;
                case "changed":
//                    builder.append(String.format("Property '%s' was updated. From %s to %s\r\n", key,
                    builder.append(String.format("Property '%s' was updated. From %s to %s\n", key,
                            valueToString(keyData.get("value1")), valueToString(keyData.get("value2"))));
                    break;
                default:
                    throw new RuntimeException("Unexpected value: " + operand);
            }
        }
//        builder.replace(builder.lastIndexOf("\r\n"), builder.length(), "");
        builder.replace(builder.lastIndexOf("\n"), builder.length(), "");
        return builder.toString();
    }

    private static String valueToString(Object value) {
        if (value != null) {
            if (value instanceof List<?>
                    || value instanceof Map<?, ?>) {
                return "[complex value]";
            }
            if (!(value instanceof Boolean) && !(value instanceof Number)) {
                return "'" + value + "'";
            }
        } else {
            return null;
        }
        return String.valueOf(value);
    }
}
