package com.befrank.casedeveloperjava.services;

import com.befrank.casedeveloperjava.mockapi.InvestmentServiceClient;
import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.models.Pension;
import com.befrank.casedeveloperjava.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PensionService {

    private final ParticipantRepository participantRepository;
    private final InvestmentServiceClient investmentServiceClient;

    public PensionService(ParticipantRepository participantRepository, InvestmentServiceClient investmentServiceClient) {
        this.participantRepository = participantRepository;
        this.investmentServiceClient = investmentServiceClient;
    }

    public Pension getExpectedPensionValue(long id, int expectedPensionAge) {
        Participant participant = getParticipant(id);
        long pensionAccountNumber = participant.getPensionAccountNumber();
        BigDecimal currentPensionValue = getCurrentPensionValueForAccount(pensionAccountNumber);
        PensionCalculator pensionCalculator = new PensionCalculator();
        BigDecimal expectedPension = pensionCalculator.calculatePensionForAge(participant, currentPensionValue, expectedPensionAge);

        return Pension.builder()
                .expectedPension(expectedPension)
                .forAge(expectedPensionAge)
                .build();
    }

    private Participant getParticipant(long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isEmpty()) {
            throw new ParticipantNotFoundException(String.format("Could not find participant for id: %s", id));
        }
        return participant.get();
    }

    private BigDecimal getCurrentPensionValueForAccount(long pensionAccountNumber) {
        BigDecimal currentValue = investmentServiceClient.getCurrentValue(pensionAccountNumber);
        if (currentValue == null) {
            throw new PensionAccountNotFoundException(String.format("Could not find Pension account for number: %s", pensionAccountNumber));
        }
        return currentValue;
    }

}
