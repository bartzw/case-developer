package com.befrank.casedeveloperjava.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeUtils {

    private AgeUtils() {}

    public static int getAgeInYears(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public static int calculateYears(LocalDate dateOfBirth, int expectedAge) {
        return expectedAge - getAgeInYears(dateOfBirth);
    }
}
