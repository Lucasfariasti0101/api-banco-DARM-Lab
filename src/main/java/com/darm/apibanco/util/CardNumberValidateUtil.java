package com.darm.apibanco.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CardNumberValidateUtil {
    static HashMap<String, String> flagsRegex = new HashMap<>();

    public CardNumberValidateUtil() {
        flagsRegex.put("Visa","^4[0-9]{12}(?:[0-9]{3})");
        flagsRegex.put("Mastercard","^5[1-5][0-9]{14}");
        flagsRegex.put("Amex","^3[47][0-9]{13}");
        flagsRegex.put("DinersClub","^3(?:0[0-5]|[68][0-9])[0-9]{11}");
        flagsRegex.put("Discover","^6(?:011|5[0-9]{2})[0-9]{12}");
        flagsRegex.put("JCB","^(?:2131|1800|35\\d{3})\\d{11}");
    }

    public static String validateAndReturnFlag(String number) {

        String formattedNumber = formatNumber(number);

        return flagsRegex.entrySet()
                .stream()
                .filter(m -> Pattern.matches(m.getValue(), formattedNumber))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new).toUpperCase();
    }

    public static String formatNumber(String number) {
        boolean digitMatches = number.matches("\\d");
        if (!digitMatches) {
            throw new RuntimeException("");
        }
        return number.replaceAll("\\s", "");

    }

}

