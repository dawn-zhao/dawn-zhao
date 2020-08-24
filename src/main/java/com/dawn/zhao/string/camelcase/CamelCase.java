package com.dawn.zhao.string.camelcase;

public class CamelCase {

    public static void main(String[] args) {
        System.out.println(toSnakeCase("HaHaHa"));
        System.out.println(toCamelCase("ha_ha_ha"));
    }

    /**
     * 驼峰转下划线
     * @param in
     * @return
     */
    private static String toSnakeCase(String in) {
        String result = "" + Character.toLowerCase(in.charAt(0));
        for (int i = 1; i < in.length(); ++i) {
            char c = in.charAt(i);
            result = Character.isUpperCase(c) ? result + "_" + Character.toLowerCase(c) : result + c;
        }
        return result;
    }

    /**
     * 下划线转驼峰
     * @param in
     * @return
     */
    private static String toCamelCase(String in) {
        String camelCased = "";
        for (String token : in.split("_")) {
            camelCased = token.length() >= 1 ? camelCased + token.substring(0, 1).toUpperCase() + token.substring(1) : camelCased + "_";
        }
        return camelCased;
    }
}
