package com.linewx.parser.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

/**
 * Created by luganlin on 11/22/16.
 */
public class AmountUtil {
    private static Pattern amountPattern;
    private static Map<Character, Character> numberMapping = new HashMap<>();
    static {
        amountPattern = Pattern.compile("([\\d|，]+)元");
        numberMapping.put('〇', '0');
        numberMapping.put('一', '1');
        numberMapping.put('二', '2');
        numberMapping.put('三', '3');
        numberMapping.put('四', '4');
        numberMapping.put('五', '5');
        numberMapping.put('六', '6');
        numberMapping.put('七', '7');
        numberMapping.put('八', '8');
        numberMapping.put('九', '9');
    }

    public static void main(String []argv) {
           //parseMainAmount("原告金额:[：, 一、被告戴兴勇应于本判决发生法律效力之日起十五234，234元日内向原元告蒋继超给付工程款388206元；, 如果]");
        //System.out.print(Arrays.asList("6亿".split("亿")));
        //String a = "6亿".split("亿");
        System.out.println(ParseLong("二十二"));
    }

    public static Long parseMainAmount(String amountText) {

        Matcher matcher = amountPattern.matcher(amountText);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        return null;
    }

    //一亿六千七百万八千六十元
    public static Long ParseLong(String number) {
        if(number.contains("亿")) {
            String[] numbers = number.split("亿");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }
            
            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }
            
            return ParseLong(leftNumber) * 100000000L + ParseLong(rightNumber);
        }

        if(number.contains("万")) {
            String[] numbers = number.split("万");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }

            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }

            return ParseLong(leftNumber) * 10000L + ParseLong(rightNumber);
        }

        if(number.contains("千")) {
            String[] numbers = number.split("千");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }

            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }

            return ParseLong(leftNumber) * 1000L + ParseLong(rightNumber);
        }

        if(number.contains("百")) {
            String[] numbers = number.split("百");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }

            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }

            return ParseLong(leftNumber) * 100L + ParseLong(rightNumber);
        }

        if(number.contains("十")) {
            String[] numbers = number.split("十");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }

            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }

            return ParseLong(leftNumber) * 10L + ParseLong(rightNumber);
        }

        if(number.contains("，")) {
            String[] numbers = number.split("，");
            if (numbers.length > 2 || number.length() == 0)  {
                throw new RuntimeException("unsupported number format");
            }

            String leftNumber = numbers[0].equals("") ? "1": numbers[0];
            String rightNumber = "0";
            if (numbers.length == 2) {
                rightNumber = numbers[1];
            }

            return ParseLong(leftNumber) * 1000L + ParseLong(rightNumber);
        }


        return transfer(number);
    }

    public static Long transfer(String originNumber) {
        StringBuilder targetNumber = new StringBuilder();

        for (int i=0; i<originNumber.length(); i++) {
            Character currentChar = originNumber.charAt(i);
            targetNumber.append(numberMapping.getOrDefault(currentChar, currentChar));
        }

        return Long.parseLong(targetNumber.toString());
    }
}
