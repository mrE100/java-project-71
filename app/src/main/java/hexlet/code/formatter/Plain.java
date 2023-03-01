package hexlet.code.formatter;

import org.apache.commons.lang3.StringUtils;

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
                    throw new IllegalStateException("Unexpected value: " + operand);
            }
        }
//        builder.replace(builder.lastIndexOf("\r\n"), builder.length(), "");
        builder.replace(builder.lastIndexOf("\n"), builder.length(), "");
        return builder.toString();
    }

    private static String valueToString(String value) {
        if (value != null) {
            if ((value.startsWith("{") && value.endsWith("}"))
                    || (value.startsWith("[") && value.endsWith("]"))) {
                return "[complex value]";
            }
            if (!List.of("true", "false", "null").contains(value) && !StringUtils.isNumeric(value)) {
                return "'" + value + "'";
            }
        }
        return value;
    }
}
