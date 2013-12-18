package com.pstu.acdps.shared.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public interface IToString<T> {
        String toString(T element);
    }

    public static List<String> splitAndTrim(String source, String separator) {
        List<String> res = new ArrayList<String>();
        if (source != null) {
            String[] arr = source.split(separator);
            for (String string : arr) {
                res.add(string.trim());
            }
        }
        return res;
    }

    public static List<String> toLowerCase(List<String> source) {
        List<String> res = new ArrayList<String>(source.size());
        for (String string : source)
            res.add(string.toLowerCase());
        return res;

    }

    public static <T> String listToString(List<T> source, String separator) {
        return listToString(source, null, separator);
    }

    public static <T> String listToString(List<T> source, IToString<T> toString, String separator) {
        if (source == null) return "";

        String res = "";
        for (T t : source) {
            String text = toString == null ? t.toString() : toString.toString(t);
            res += text + separator;
        }
        if (res.length() > separator.length()) res = res.substring(0, res.length() - separator.length());
        return res;
    }

    public static boolean isEmpty(Object string) {
        return string == null || (string + "").isEmpty();
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
