package com.takima.race.runner.controllers;

import com.takima.race.runner.entities.Race;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RegistrationService;
import com.takima.race.runner.services.RunnerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;
    private final RegistrationService registrationService;

    public RunnerController(RunnerService runnerService, RegistrationService registrationService) {
        this.runnerService = runnerService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    @PostMapping
    public Runner createRunner(@RequestBody Runner runner) {
        return runnerService.createRunner(runner);
    }

    @DeleteMapping("/{id}")
    public void deleteRunner(@PathVariable Long id) {
        runnerService.deleteRunner(id);
    }

    @PutMapping("/{id}")
    public Runner updateRunner(@PathVariable Long id, @RequestBody Runner runner) {
        return runnerService.updateRunner(id, runner);
    }

    @GetMapping("/{runnerId}/races")
    public List<Race> getRaces(@PathVariable Long runnerId) {
        return registrationService.getRacesOfRunner(runnerId);
    }

}
