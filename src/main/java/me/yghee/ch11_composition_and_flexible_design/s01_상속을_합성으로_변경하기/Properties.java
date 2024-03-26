package me.yghee.ch11_composition_and_flexible_design.s01_상속을_합성으로_변경하기;

import java.util.Hashtable;

public class Properties {
    private Hashtable<String, String> properties = new Hashtable<>();

    public String setProperty(String key, String value) {
        return properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }
}
