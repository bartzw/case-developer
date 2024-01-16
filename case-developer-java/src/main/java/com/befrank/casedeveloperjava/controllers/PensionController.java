package com.befrank.casedeveloperjava.controllers;

import com.befrank.casedeveloperjava.models.Pension;
import com.befrank.casedeveloperjava.services.PensionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PensionController {

    private final PensionService pensionService;

    @GetMapping("/pension/{id}")
    public Pension calculate(@PathVariable Long id, @RequestParam int verwachtePensioenLeeftijd ) {
        return pensionService.getExpectedPensionValue( id, verwachtePensioenLeeftijd );
    }
}
