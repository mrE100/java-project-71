package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {


    public static Map<String, String> parse(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, String> value = mapper.readValue(content, Map.class);
        return value;
    }
}
