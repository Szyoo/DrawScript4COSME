package com.szyoo.jackson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {
    private final static String FILEPATH = "src/JSONLog.txt";

    /**
     * 传入Json字符串后覆写到本地默认路径
     * 
     * @param jsonString
     */
    public static void writeJson(String jsonString) {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("数据文件保存成功");
    }

    /**
     * 从默认路径读取文件，写入缓存并以String返回，若文件不存在则返回空字符串
     * 
     * @return jsonString
     */
    public static String readJson() {
        File file = new File(FILEPATH);
        if (file.exists()) {
            BufferedReader br = null;
            StringBuffer sb = new StringBuffer();
            try {
                br = new BufferedReader(new FileReader(file));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                    sb.append("\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("历史数据文件读取成功");
            return sb.toString();
        } else {
            return "";
        }
    }

}
