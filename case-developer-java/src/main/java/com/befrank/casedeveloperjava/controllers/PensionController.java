package com.befrank.casedeveloperjava.controllers;

import com.befrank.casedeveloperjava.services.PensionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/pension")
@AllArgsConstructor
public class PensionController {

    private final PensionService pensionService;

    @GetMapping("/{id}")
    public BigDecimal calculate(@PathVariable Long id, @RequestParam int verwachtePensioenLeeftijd ) {
        return pensionService.getExpectedPensionValue( id, verwachtePensioenLeeftijd );
    }
}
