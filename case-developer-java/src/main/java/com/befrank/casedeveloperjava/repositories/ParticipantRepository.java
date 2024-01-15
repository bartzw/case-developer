package com.befrank.casedeveloperjava.repositories;

import com.befrank.casedeveloperjava.models.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> { }
