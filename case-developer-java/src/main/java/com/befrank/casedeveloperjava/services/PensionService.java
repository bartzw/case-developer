package com.befrank.casedeveloperjava.services;

import com.befrank.casedeveloperjava.mockapi.InvestmentServiceClient;
import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PensionService {

    ParticipantRepository participantRepository;
    InvestmentServiceClient investmentServiceClient;

    public PensionService(ParticipantRepository participantRepository, InvestmentServiceClient investmentServiceClient) {
        this.participantRepository = participantRepository;
        this.investmentServiceClient = investmentServiceClient;
    }

    public BigDecimal getExpectedPensionValue(long id, int expectedPensionAge) {
        Participant participant = getParticipant(id);
        long pensionAccountNumber = participant.getPensionAccountNumber();
        BigDecimal currentPensionValue = getCurrentPensionValueForAccount(pensionAccountNumber);
        PensionCalculator pensionCalculator = new PensionCalculator();

        return pensionCalculator.calculatePensionForAge(participant, currentPensionValue, expectedPensionAge);
    }

    private Participant getParticipant(long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        if (participant.isEmpty()) {
            throw new ParticipantNotFoundException();
        }
        return participant.get();
    }

    private BigDecimal getCurrentPensionValueForAccount(long pensionAccountNumber) {
        BigDecimal currentValue = investmentServiceClient.getCurrentValue(pensionAccountNumber);
        if (currentValue == null) {
            throw new PensionAccountNotFoundException();
        }
        return currentValue;
    }

}
