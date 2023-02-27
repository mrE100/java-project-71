package hexlet.code.formatter;


import java.util.List;

public class Stylish {

    public static String format(List<String[]> data) {
        StringBuilder builder = new StringBuilder();
//        builder.append("{\r\n");
        builder.append("{\n");
        String operation;
        String keyValue;
        String key;
        for (String[] string : data){
            String operand = string[0];
            key = string[1];
            keyValue = string[2];
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
