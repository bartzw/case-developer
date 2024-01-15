package com.befrank.casedeveloperjava.controllers;

import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.repositories.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ParticipantController {

    private final ParticipantRepository participantRepository;

    @GetMapping("/deelnemers/{id}")
    public Optional<Participant> getParticipant(@PathVariable Long id) {
        return participantRepository.findById(id);
    }

}