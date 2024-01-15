package com.befrank.casedeveloperjava;

import com.befrank.casedeveloperjava.models.Employment;
import com.befrank.casedeveloperjava.models.Participant;
import com.befrank.casedeveloperjava.repositories.ParticipantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories
public class CaseDeveloperJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseDeveloperJavaApplication.class, args);
	}


	@Bean
	CommandLineRunner init(ParticipantRepository participantRepository) {
		return args -> {
			Employment employment = Employment.builder()
					.fulltimeSalary(BigDecimal.valueOf(60000.00))
					.parttimePercentage(BigDecimal.valueOf(80.00))
					.franchise(BigDecimal.valueOf(15599.00))
					.availablePremium(BigDecimal.valueOf(5.00))
					.build();
			Participant participant = Participant.builder()
					.name("Bart")
					.address("address 10, 1012AB, Amsterdam")
					.placeOfBirth("Zaandam")
					.isEmployed(true)
					.dateOfBirth(LocalDate.of(1964,1,1))
					.email("bart@domain.com")
					.employment(employment)
					.pensionAccountNumber(1000)
					.build();
			participantRepository.save(participant);
		};
	}
}
