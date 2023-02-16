package hexlet.code;

//import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")


public class App implements Callable<Object> {
    @Parameters(index = "0", description = "path to first file")
    private Path filepath1;

    @Parameters(index = "1", description = "path to second file")
    private Path filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

//    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object call() throws Exception {

//        Map<String, String> firstFile = new HashMap<>();
//        Map<String, String> secondFile = new HashMap<>();

        Path path1 = Paths.get(String.valueOf(filepath1)).toAbsolutePath().normalize();
        Map<String, String> value1 = Parser.parse(Files.readString(path1));
        Path path2 = Paths.get(String.valueOf(filepath2)).toAbsolutePath().normalize();
        Map<String, String> value2 = Parser.parse(Files.readString(path2));
        System.out.println(Differ.generate(value1, value2));

        return 0;
    }

//    private static Map<String, String> parse(String content) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> value = mapper.readValue(content, Map.class);
//        return value;
//    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
//        Map<String, String> firstFile = new HashMap<>();
//        Map<String, String> secondFile = new HashMap<>();
//        String filepath1 = "./app/filepath1.json";
//        String filepath2 = "./app/filepath2.json";
//        try {
//            Path path1 = Paths.get(String.valueOf(filepath1)).toAbsolutePath().normalize();
//            Map<String, String> value1 = parse(Files.readString(path1));
//            System.out.println(value1);
//            Path path2 = Paths.get(String.valueOf(filepath2)).toAbsolutePath().normalize();
//            Map<String, String> value2 = parse(Files.readString(path2));
//            System.out.println(Differ.generate(value1, value2));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

}
