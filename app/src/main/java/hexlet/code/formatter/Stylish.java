package hexlet.code.formatter;

import java.util.Map;

public class Stylish {
    private static final Map<String, String> OPERATION_MAP = Map.of(
            "deleted", "  - ",
            "added", "  + ",
            "unchanged", "    ",
            "changed", "  - "
    );

    public static String format(Map<String, Map<String, Object>> data) {
        StringBuilder builder = new StringBuilder();
//        builder.append("{\r\n");
        builder.append("{\n");
        String operation;
        Object keyValue;
        for (String key : data.keySet()) {
            Map<String, Object> keyData = data.get(key);
            var operand = keyData.get("type");
            keyValue = keyData.get("value");
            operation = OPERATION_MAP.get(operand);
            if (operand.equals("changed")) {
//                    builder.append(operation).append(key).append(": ").append(keyData.get("value1")).append("\r\n");
                builder.append(operation).append(key).append(": ").append(keyData.get("value1")).append("\n");
                operation = OPERATION_MAP.get("added");
                keyValue = keyData.get("value2");
            }
//            builder.append(operation).append(key).append(": ").append(keyValue).append("\r\n");
            builder.append(operation).append(key).append(": ").append(keyValue).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
