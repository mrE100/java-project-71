package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String format) throws Exception {
        Map<String, Object> result = switch (format) {
            case "json" -> Parser.parseJson(content);
            case "yml", "yaml" -> Parser.parseYaml(content);
            default -> throw new Exception("Unknown format");
        };
        return result;
    }

    private static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return stringifyMapValues(mapper.readValue(content, Map.class));
    }

    private static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return stringifyMapValues(mapper.readValue(content, Map.class));
    }

    private static Map stringifyMapValues(Map<String, Object> data) {
        HashMap<String, String> result = new HashMap<>();
        for (String key : data.keySet()) {
            Object value = data.get(key);
            result.put(key, String.valueOf(value));
        }
        return result;
    }
}
