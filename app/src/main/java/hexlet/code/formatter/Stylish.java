package hexlet.code.formatter;


import java.util.Map;

public class Stylish {

    public static String format(Map<String, Map<String, String>> data) {
        StringBuilder builder = new StringBuilder();
//        builder.append("{\r\n");
        builder.append("{\n");
        String operation;
        String keyValue;
        for (String key : data.keySet()) {
            Map<String, String> keyData = data.get(key);
            String operand = keyData.get("type");
            keyValue = keyData.get("value");
            switch (operand) {
                case "deleted":
                    operation = "   - ";
                    break;
                case "added":
                    operation = "   + ";
                    break;
                case "unchanged":
                    operation = "     ";
                    break;
                case "changed":
                    operation = "   - ";
//                    builder.append(operation).append(key).append(": ").append(keyData.get("value1")).append("\r\n");
                    builder.append(operation).append(key).append(": ").append(keyData.get("value1")).append("\n");
                    operation = "   + ";
                    keyValue = keyData.get("value2");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operand);
            }
//            builder.append(operation).append(key).append(": ").append(keyValue).append("\r\n");
            builder.append(operation).append(key).append(": ").append(keyValue).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
