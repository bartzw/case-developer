package com.befrank.casedeveloperjava.services;

import com.befrank.casedeveloperjava.models.Employment;
import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.utils.AgeUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PensionCalculator {

    private static final BigDecimal RETURN_RATE = BigDecimal.valueOf(0.03);

    public BigDecimal calculatePensionForAge(Participant participant, BigDecimal currentPensionValue,
                                                int expectedPensionAge) {
        BigDecimal yearlyPremium = calculateYearlyPremium(participant);
        int yearsToCalculate = AgeUtils.calculateYears(participant.getDateOfBirth(), expectedPensionAge);

        for (int year = 1; year <= yearsToCalculate; year++) {
            BigDecimal returns = getReturns(currentPensionValue, yearlyPremium);
            currentPensionValue = currentPensionValue
                    .add(yearlyPremium)
                    .add(returns);
        }
        return currentPensionValue.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getReturns(BigDecimal currentPensionValue, BigDecimal yearlyPremium) {
        return currentPensionValue
                .add(yearlyPremium.divide(BigDecimal.valueOf(2)))
                .multiply(RETURN_RATE);
    }

    private BigDecimal calculateYearlyPremium(Participant participant) {
        if (participant.isEmployed()) {
            Employment employment = participant.getEmployment();
            BigDecimal fullTimeSalary = employment.getFulltimeSalary();
            BigDecimal parttimePercentage = employment.getParttimePercentage()
                    .divide(BigDecimal.valueOf(100));
            BigDecimal franchise = employment.getFranchise();
            BigDecimal premiumPercentage = employment.getAvailablePremium()
                    .divide(BigDecimal.valueOf(100));

            return fullTimeSalary
                    .subtract(franchise)
                    .multiply(parttimePercentage)
                    .multiply(premiumPercentage).setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.valueOf(0);
    }
}
