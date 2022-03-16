package br.com.ialmeida.projetofinaldesenvolvimentoweb.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class ApiUtil {

    public static final int FOOD_SCORE = 1;
    public static final int WATER_SCORE = 2;
    public static final int AMMUNITION_SCORE = 3;
    public static final int GUN_SCORE = 4;

    public static String decodeParam(String text) {
        return stripAccents(URLDecoder.decode(text, StandardCharsets.UTF_8));
    }

    // https://stackoverflow.com/questions/15190656/easy-way-to-remove-accents-from-a-unicode-string
    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}
