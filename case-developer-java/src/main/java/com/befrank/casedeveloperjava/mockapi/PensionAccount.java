package com.befrank.casedeveloperjava.mockapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionAccount {

    private long pensionAccountNumber;
    private BigDecimal currentValue;
}
