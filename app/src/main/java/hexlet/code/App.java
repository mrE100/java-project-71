package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")


public class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 1;
    private static final int ERROR_EXIT_CODE = 1;
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

//    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
            return SUCCESS_EXIT_CODE;
        } catch (Exception e) {
            return ERROR_EXIT_CODE;
        }
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
