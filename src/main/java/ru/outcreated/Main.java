package ru.outcreated;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("\nДобро пожаловать в Meta Calculator");
        System.out.println("Поддерживаемые операции на данный момент - [сложение|вычитание|умножение|деление]");
        meta();
    }

    public static void meta() throws Exception {
        System.out.println("\bВведите выражение в формате [num <operator> num]");
        System.out.println("\nРезультат операции: " + calculate(scanner.nextLine()));
        meta();
    }

    public static void validateInputNumbers(int first, int second) throws Exception {
        if (first < 1 || second < 1) {
            throw new Exception("Число не может быть меньше еденицы");
        } else if (first > 10 || second > 10) {
            throw new Exception("Число не может быть больше десяти");
        }
    }

    public static String calculate(String s) throws Exception {
        String[] numbers = s.split("(\\+)|(-)|(\\*)|(/)");

        if (numbers.length > 2 || numbers.length == 1) throw new Exception("Неверное количество аргументов операции");

        String result = "";
        String[] bal = new String[]{
                numbers[0].replaceAll("\\s", ""),
                numbers[1].replaceAll("\\s", "")
        };

        if (Character.isDigit(bal[0].charAt(0))) {
            if (Character.isDigit(bal[1].charAt(0))) {
                validateInputNumbers(Integer.parseInt(bal[0]), Integer.parseInt(bal[1]));
                if (s.contains("+")) {
                    result = String.valueOf(Integer.parseInt(bal[0]) + Integer.parseInt(bal[1]));
                } else if (s.contains("-")) {
                    if ((Integer.parseInt(bal[0]) - Integer.parseInt(bal[1])) < 0) {
                        throw new Exception("Результат не может быть отрицательным");
                    }
                    result = String.valueOf(Integer.parseInt(bal[0]) - Integer.parseInt(bal[1]));
                } else if (s.contains("*")) {
                    result = String.valueOf(Integer.parseInt(bal[0]) * Integer.parseInt(bal[1]));
                } else if (s.contains("/")) {
                    result = String.valueOf(Integer.parseInt(bal[0]) / Integer.parseInt(bal[1]));
                }
            } else {
                throw new Exception("Второе число не является арабским");
            }
        } else if (Character.isAlphabetic(bal[0].charAt(0))) {
            if (Character.isAlphabetic(bal[1].charAt(0))) {
                validateInputNumbers(romanToArabic(bal[0]), romanToArabic(bal[1]));
                if (s.contains("+")) {
                    result = arabicToRoman(romanToArabic(bal[0]) + romanToArabic(bal[1]));
                } else if (s.contains("-")) {
                    if ((romanToArabic(bal[0]) - romanToArabic(bal[1])) < 0) {
                        throw new Exception("Результат не может быть отрицательным");
                    }
                    result = arabicToRoman(romanToArabic(bal[0]) - romanToArabic(bal[1]));
                } else if (s.contains("*")) {
                    result = arabicToRoman(romanToArabic(bal[0]) * romanToArabic(bal[1]));
                } else if (s.contains("/")) {
                    result = arabicToRoman(romanToArabic(bal[0]) / romanToArabic(bal[1]));
                }
            } else {
                throw new Exception("Второе число не является римским");
            }
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        List<RomanArabic> romanNumerals = RomanArabic.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while ((number > 0) && (i < romanNumerals.size())) {
            RomanArabic currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else i++;
        }
        return sb.toString();
    }

    public static int romanToArabic(String string) {
        String input = string.toUpperCase();
        List<RomanArabic> numerals = RomanArabic.getReverseSortedValues();
        int i = 0, result = 0;
        while ((!input.isEmpty()) && (i < numerals.size())) {
            RomanArabic symbol = numerals.get(i);
            if (input.startsWith(symbol.name())) {
                result += symbol.getValue();
                input = input.substring(symbol.name().length());
            } else i++;
        }

        return result;
    }

}