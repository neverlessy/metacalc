package ru.outcreated;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanArabic {
    I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100);

    private int value;
    RomanArabic(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanArabic> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanArabic e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
