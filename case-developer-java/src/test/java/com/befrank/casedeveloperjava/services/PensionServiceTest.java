package com.befrank.casedeveloperjava.services;

import com.befrank.casedeveloperjava.mockapi.InvestmentServiceClient;
import com.befrank.casedeveloperjava.models.Employment;
import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.repositories.ParticipantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PensionServiceTest {

    @Mock
    Participant participant;
    @Mock
    Employment employment;
    @Mock
    ParticipantRepository participantRepository;

    PensionService pensionService;

    @BeforeEach
    void setup() {
        pensionService = new PensionService(participantRepository, new InvestmentServiceClient());
    }

    @ParameterizedTest
    @CsvSource({"125498.08, 65",
            "104802.68, 61"})
    void getExpectedPensionValue_whenParticipantIsEmployed(double expectedPensionValue, int expectedPensionAge) {
        when(participantRepository.findById(1L)).thenReturn(Optional.ofNullable(participant));
        when(participant.getDateOfBirth()).thenReturn(LocalDate.now().minusYears(60L));
        when(participant.getPensionAccountNumber()).thenReturn(1000L);
        when(participant.getEmployment()).thenReturn(employment);
        when(participant.isEmployed()).thenReturn(true);
        when(employment.getFulltimeSalary()).thenReturn(BigDecimal.valueOf(60000.00));
        when(employment.getParttimePercentage()).thenReturn(BigDecimal.valueOf(80));
        when(employment.getAvailablePremium()).thenReturn(BigDecimal.valueOf(5));
        when(employment.getFranchise()).thenReturn(BigDecimal.valueOf(15599.00));

        Assertions.assertEquals(BigDecimal.valueOf(expectedPensionValue), pensionService.getExpectedPensionValue(1L, expectedPensionAge).getExpectedPension());
    }

    @Test
    void getExpectedPensionValue_whenParticipantIsNotFound() {
        when(participantRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ParticipantNotFoundException.class, () -> pensionService.getExpectedPensionValue(1L, 60));
    }

    @Test
    void getExpectedPensionValue_whenPensionAccountIsNotFound() {
        when(participantRepository.findById(1L)).thenReturn(Optional.ofNullable(participant));
        when(participant.getPensionAccountNumber()).thenReturn(1001L);

        Assertions.assertThrows(PensionAccountNotFoundException.class, () -> pensionService.getExpectedPensionValue(1L, 60));
    }

    @Test
    void calculateYearlyPremium_whenParticipantIsNotEmployed() {
        when(participantRepository.findById(1L)).thenReturn(Optional.ofNullable(participant));
        when(participant.getDateOfBirth()).thenReturn(LocalDate.now().minusYears(60L));
        when(participant.getPensionAccountNumber()).thenReturn(1000L);
        when(participant.isEmployed()).thenReturn(false);

        Assertions.assertEquals(BigDecimal.valueOf(103000.00).setScale(2, RoundingMode.HALF_UP), pensionService.getExpectedPensionValue(1L, 61).getExpectedPension());
    }
}
