package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonFieldCount {
    public static void main(String[] args) {
        // 1. 读取 JSON 文件
        String filePath = "/Users/dawn/Dawn/bate_test/jsonarry/src/main/resources/dump.json";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> fieldCounts = new HashMap<>();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            countFieldValues(rootNode, "value", fieldCounts);

            for (Map.Entry<String, Integer> entry : fieldCounts.entrySet()) {
                System.out.println("Field Value: " + entry.getKey() + ", Count: " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countFieldValues(JsonNode node, String fieldName, Map<String, Integer> fieldCounts) {
        if (node.isObject()) {
            JsonNode fieldNode = node.get(fieldName);
            if (fieldNode != null && fieldNode.isTextual()) {
                String fieldValue = fieldNode.asText();
                fieldCounts.put(fieldValue, fieldCounts.getOrDefault(fieldValue, 0) + 1);
            }
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                countFieldValues(entry.getValue(), fieldName, fieldCounts);
            }
        } else if (node.isArray()) {
            for (JsonNode arrayNode : node) {
                countFieldValues(arrayNode, fieldName, fieldCounts);
            }
        }
    }
}
