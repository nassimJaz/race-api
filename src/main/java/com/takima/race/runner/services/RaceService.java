package com.takima.race.runner.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.runner.entities.Race;
import com.takima.race.runner.repositories.RaceRepository;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Race not found", id)
            )
        );
    }

    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

}
