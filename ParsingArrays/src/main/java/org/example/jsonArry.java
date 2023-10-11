package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class jsonArry {
    public static void main(String[] args) {
        try {
            // 读取 JSON 文件
            FileReader fileReader = new FileReader("/Users/dawn/Dawn/bate_test/jsonarry/src/main/resources/dump.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);

            // 创建 JSON 数组来存储唯一的 JSON 数据对象
            JSONArray uniqueData = new JSONArray();
            Set<String> jsonStrings = new HashSet<>();

            // 解析 JSON 数据
            JSONArray jsonArray = new JSONArray(jsonTokener);

            // 遍历 JSON 数据
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String jsonString = jsonObject.toString();

                // 如果 JSON 数据对象的字符串表示不在集合中，添加到唯一的 JSON 数组
                if (!jsonStrings.contains(jsonString)) {
                    jsonStrings.add(jsonString);
                    uniqueData.put(jsonObject);
                }
            }

            // 统计重复的数量
            int duplicates = jsonArray.length() - uniqueData.length();

            // 输出结果
            System.out.println("Total JSON objects: " + jsonArray.length());
            System.out.println("Unique JSON objects: " + uniqueData.length());
            System.out.println("Duplicate JSON objects: " + duplicates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
