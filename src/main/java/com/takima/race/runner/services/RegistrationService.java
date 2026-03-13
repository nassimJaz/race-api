package com.takima.race.runner.services;

import com.takima.race.runner.entities.Race;
import com.takima.race.runner.entities.Registration;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RegistrationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RunnerService runnerService;
    private final RaceService raceService;

    public RegistrationService(RegistrationRepository registrationRepository, RunnerService runnerService, RaceService raceService) {
        this.registrationRepository = registrationRepository;
        this.runnerService = runnerService;
        this.raceService = raceService;
    }

    public Registration createRegistration(Long raceId, Long runnerId) {
        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Runner is already registered for this race");
        }

        Runner runner = runnerService.getById(runnerId);
        Race race = raceService.getById(raceId);

        if (registrationRepository.countByRaceId(raceId) >= race.getMaxParticipants()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Race is full");
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

    public List<Runner> getRegistrations(Long raceId) {
        List<Registration> registrations = registrationRepository.findRegistrationByRaceId(raceId);
        List<Runner> runnersRegistrated = new ArrayList<Runner>();

        for (Registration registration : registrations) {
            runnersRegistrated.add(registration.getRunner());
        }

        return runnersRegistrated;
    }

    public List<Race> getRacesOfRunner(Long runnerId) {
        List<Registration> registrations = registrationRepository.findRegistrationByRunnerId(runnerId);
        List<Race> racesOfRunner = new ArrayList<Race>();

        for (Registration registration : registrations) {
            racesOfRunner.add(registration.getRace());
        }

        return racesOfRunner;
    }

    public long countParticipantsByRaceId(Long raceId) {
        raceService.getById(raceId); // voir si la course existe
        return registrationRepository.countByRaceId(raceId);
    }
    
    
}
