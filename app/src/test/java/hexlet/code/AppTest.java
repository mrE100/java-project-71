package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.*;



public class AppTest {
//    private static String resourcesPath;
    private static String jsonFilePath1;
    private static String jsonFilePath2;
    private static String yamlFilePath1;
    private static String yamlFilePath2;

    @BeforeAll
    public static void beforeAll() {
//        resourcesPath = new File(".").getAbsolutePath();

        jsonFilePath1 = "filepath1.json";
        jsonFilePath2 = "filepath2.json";

        yamlFilePath1 = "filepath1.yml";
        yamlFilePath2 = "filepath2.yml";
    }

    @Test
    public void testJson() throws Exception {
        testAbstract(jsonFilePath1, jsonFilePath2);
    }

    @Test
    public void testYaml() throws Exception {
        testAbstract(yamlFilePath1, yamlFilePath2);
    }

    @DisplayName("'main' method works correctly")

    private void testAbstract(String filePath1, String filePath2) throws Exception {
        var path = Paths.get("src\\test\\resources\\expected").toAbsolutePath().normalize();
        var expected = Files.readString(path);
        var actual = Differ.generate(filePath1, filePath2);

        assertEquals(expected, actual);
    }
}
