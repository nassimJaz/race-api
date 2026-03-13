package com.takima.race.runner.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.runner.entities.Race;
import com.takima.race.runner.entities.Registration;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RaceService;
import com.takima.race.runner.services.RegistrationService;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;
    private final RegistrationService registrationService;

    public RaceController(RaceService raceService, RegistrationService registrationService) {
        this.raceService = raceService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Race> getAll() {
        return raceService.getAll();
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    public Race createRace(@RequestBody Race race) {
        return raceService.createRace(race);
    }

    @PostMapping("/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration registration(@PathVariable Long raceId, @RequestBody java.util.Map<String, Long> body) {
        return registrationService.createRegistration(raceId, body.get("runnerId"));
    }

    @GetMapping("/{raceId}/registrations")
    public List<Runner> getRegistrations(@PathVariable Long raceId) {
        return registrationService.getRegistrations(raceId);
    }

    @GetMapping("/{raceId}/participants/count")
    public java.util.Map<String, Long> countParticipants(@PathVariable Long raceId) {
        return java.util.Collections.singletonMap("count", registrationService.countParticipantsByRaceId(raceId));
    }


}
