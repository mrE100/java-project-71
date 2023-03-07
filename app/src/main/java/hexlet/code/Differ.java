package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map value1 = getData(filepath1);
        Map value2 = getData(filepath2);
        TreeMap data = DiffBuilder.generateDiff(value1, value2);
        return Formatter.getOutputText(data, format);
    }

    private static Map getData(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String format = getFormat(path);
        return Parser.parse(Files.readString(path), format);
    }

    private static String getFormat(Path path) {
        String fileNameStr = path.getFileName().toString();
        int lastDot = fileNameStr.lastIndexOf('.');
        if (lastDot == -1) {
            return "";
        } else {
            return fileNameStr.substring(lastDot + 1);
        }
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}
