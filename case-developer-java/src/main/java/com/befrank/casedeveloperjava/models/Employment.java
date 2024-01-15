package com.befrank.casedeveloperjava.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employment {

    private BigDecimal fulltimeSalary;
    private BigDecimal parttimePercentage;
    private BigDecimal franchise;
    private BigDecimal availablePremium;
}
