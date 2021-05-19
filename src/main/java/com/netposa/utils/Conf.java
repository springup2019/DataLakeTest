package com.netposa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author JuHan
 * @date 2021-05-08
 */
public class Conf {
    private static Properties p = null;

    /**
     * 加载配置文件
     *
     * @param confFilePath
     */
    public static void loadConf(String confFilePath) {
        String confFilePathAfterFormat = confFilePath.endsWith("/") ? confFilePath : confFilePath + "/";
        p = new Properties();
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(confFilePathAfterFormat + "conf.properties"), StandardCharsets.UTF_8);
//            reader = new InputStreamReader(Conf.class.getClassLoader().getResourceAsStream("conf.properties"), StandardCharsets.UTF_8);
            p.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
    }

    public static String get(String key, String defaultValue) {
        String v = p.getProperty(key);
        return v == null ? defaultValue : v;
    }

    public static int get(String key, int defaultValue) {
        return Integer.valueOf(get(key, defaultValue + ""));
    }

    public static long get(String key, long defaultValue) {
        return Long.valueOf(get(key, defaultValue + ""));
    }

    public static double get(String key, double defaultValue) {
        return Double.valueOf(get(key, defaultValue + ""));
    }

    public static float get(String key, float defaultValue) {
        return Float.valueOf(get(key, defaultValue + ""));
    }

    public static boolean get(String key, boolean defaultValue) {
        return Boolean.valueOf(get(key, defaultValue + ""));
    }

    public static String get(String key) {
        return get(key, null);
    }

}
