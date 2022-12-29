package com.szyoo.jackson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szyoo.Present;

public class JsonConverter {
    /**
     * 转换单个Present对象为Json字符串
     * 
     * @param present
     * @return jsonString
     * @throws JsonProcessingException
     */
    private static String toJSON(Present present) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String presentJson = mapper.writeValueAsString(present);
        // System.out.println("Change to JSON: " + presentJson);
        return presentJson;
    }

    /**
     * 转换单个Json字符串为Present对象
     * 
     * @param jsonString
     * @return Present
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private static Present toPresent(String jsonString) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Present present = mapper.readValue(jsonString, Present.class);
        // System.out.println("Change to Present" + present.toString());
        return present;
    }

    /**
     * 转换整个Present的List为单个Json字符串，以'@'为分隔符
     * 
     * @param presents
     * @return jsonString
     * @throws JsonProcessingException
     */
    private static String listToJson(List<Present> presents) throws JsonProcessingException {
        String jsonString = "";
        for (Present p : presents) {
            jsonString = jsonString.concat(toJSON(p)).concat("\r\n");
        }
        return jsonString;
    }

    /**
     * 以'@'为分隔符，转换Json字符串为多个Present，并拼接为List
     * 
     * @param jsonString
     * @return List<Present>
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private static List<Present> listToPresents(String jsonString) {
        List<Present> presents = new ArrayList<Present>();
        try {
            if (jsonString.contains("\r\n")) {
                String[] jsonStringList = jsonString.split("\r\n");
                for (String s : jsonStringList) {
                    presents.add(toPresent(s));
                }
            } else {
                presents.add(toPresent(jsonString));
            }
        } catch (JsonProcessingException e) {
            System.out.println("Json对象转换失败！");
            e.printStackTrace();
        }
        return presents;
    }

    public static List<Present> updateList(List<Present> presents) {
        String jsonString = JsonFile.readJson();
        List<Present> presentsNew = new ArrayList<Present>();
        if (!jsonString.equals("")) {
            List<Present> presents_old = listToPresents(jsonString);
            presentsNew = Present.unionPresent(presents, presents_old);
        } else {
            return presents;
        }
        return presentsNew;
    }

    public static void writeJsonFile(List<Present> presents) {
        try {
            JsonFile.writeJson(listToJson(presents));
        } catch (JsonProcessingException e) {
            System.out.println("文件写入失败！");
            e.printStackTrace();
        }
    }
}
