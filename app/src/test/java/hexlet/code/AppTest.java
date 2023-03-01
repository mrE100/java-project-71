package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;




public class AppTest {
//    private static String resourcesPath;
    private static String jsonFilePath1;
    private static String jsonFilePath2;
    private static String yamlFilePath1;
    private static String yamlFilePath2;

    @BeforeAll
    public static void beforeAll() {
//        resourcesPath = new File(".").getAbsolutePath();

        jsonFilePath1 = "file1.json";
        jsonFilePath2 = "file2.json";

        yamlFilePath1 = "file1.yml";
        yamlFilePath2 = "file2.yml";
    }

    @Test
    public void testJsonStylish() throws Exception {
        testStylish(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testJsonPlain() throws Exception {
        testPlain(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testYamlStylish() throws Exception {
        testStylish(yamlFilePath1, yamlFilePath2);

    }
    @Test
    public void testYamlPlain() throws Exception {
        testPlain(yamlFilePath1, yamlFilePath2);
    }

    @DisplayName("'main' method works correctly")

    private void testStylish(String filePath1, String filePath2) throws Exception {
        var path = Paths.get("src/test/resources/expectedStylish").toAbsolutePath().normalize();
        var expected = Files.readString(path);
        var actual = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual);
    }

    private void testPlain(String filePath1, String filePath2) throws Exception {
        var path = Paths.get("src/test/resources/expectedPlain").toAbsolutePath().normalize();
        var expected = Files.readString(path);
        var actual = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expected, actual);
    }
}
