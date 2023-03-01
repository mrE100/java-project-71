package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.Map;

public class Formatter {
    public static String getOutputText(Map difference, String format) {
        String outputText = switch (format) {
            case "plain" -> Plain.format(difference);
            case "stylish" -> Stylish.format(difference);
            default -> throw new RuntimeException("no such format, try plain or stylish");
        };
        return outputText;
    }
}
