package com.personal.website.utils;

/**
 * 字符串工具类
 * 
 * @author admin
 */
public class StringUtils {

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static boolean isNull(String value) {
        if (value == null || "".equals(value) || "null".equals(value)) {
            return true;
        }
        return false;
    }

    public static String getNull(String value, String defalut_value) {
        if (isNull(value)) {
            return defalut_value;
        }
        return value;
    }

    /**
     * 判断字符串是否为空字符串或Null
     *
     * @param str
     *            String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 为空判断
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空字符串且不为Null
     *
     * @param str
     *            String
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 去除字符串前后空格，全角空格
     *
     * @param source
     * @return
     */
    public static String removeBlank(String source) {
        if (source == null) {
            return "";
        } else {
            source = source.trim();
            while (source.startsWith("　")) {// 这里判断是不是全角空格
                source = source.substring(1, source.length()).trim();
            }
            while (source.endsWith("　")) {
                source = source.substring(0, source.length() - 1).trim();
            }
        }
        return source;
    }
    /**
     * 校验字符串是否是数值(包含小数与负数)
     * false : . 1. 1sr -  12. -12.
     * true: -12 -12.0 -12.056 12 12.0 12.056
     * @param str
     * @return 不是数值 true：是数值
     */
    public static Boolean isNumber(String str) {
        String regex = "-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?";
        if (str == null || !str.matches(regex)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str = "哈喽,你好,商品".split(",");
        String str2 = "";
        if (str.length > 0) {
            for (int i = 0; i < str.length; i++) {
                str2 = str2 + str[i] + ",";
            }
        }
        System.out.println(str2.substring(0, str2.length() - 1));
    }
}
