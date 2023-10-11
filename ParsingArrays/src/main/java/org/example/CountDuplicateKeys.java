package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

            public class CountDuplicateKeys {
                public static void main(String[] args) {
                    try {
                        // 读取 JSON 文件
                        FileReader fileReader = new FileReader("/Users/dawn/Dawn/bate_test/jsonarry/src/main/resources/dump.json");
                        JSONTokener jsonTokener = new JSONTokener(fileReader);

                        // 创建Map以记录值和其出现次数
                        Map<String, Integer> valueCounts = new HashMap<>();

                        // 解析 JSON 数据
                        JSONArray jsonArray = new JSONArray(jsonTokener);

                        // 遍历 JSON 数据
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            // 遍历每个键
                            for (String key : jsonObject.keySet()) {
                                // 获取值
                                Object value = jsonObject.get(key);

                                // 检查值的类型
                                if (value instanceof String) {
                                    String valueString = (String) value;

                                    if (valueCounts.containsKey(valueString)) {
                                        // 如果值已经存在于Map中，增加出现次数
                                        valueCounts.put(valueString, valueCounts.get(valueString) + 1);
                                    } else {
                                        // 如果值是第一次出现，将其添加到Map中
                                        valueCounts.put(valueString, 1);
                                    }
                                }
                            }
                        }

                        // 输出每个值的出现次数
                        for (Map.Entry<String, Integer> entry : valueCounts.entrySet()) {
                            System.out.println("Value: " + entry.getKey() + ", Count: " + entry.getValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
