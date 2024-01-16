package com.befrank.casedeveloperjava.mockapi;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class InvestmentServiceClient {

    protected static final Map<Long, BigDecimal> pensionAccounts = new HashMap<>();

    static {
        pensionAccounts.put(1000L, BigDecimal.valueOf(100000.00));
    }

    public BigDecimal getCurrentValue(long accountNumber) {
        return pensionAccounts.get(accountNumber);
    }

}
