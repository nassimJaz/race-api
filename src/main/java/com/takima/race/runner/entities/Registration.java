package com.takima.race.runner.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    private LocalDate registrationDate;

    public Registration() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Runner getRunner() {
        return runner;
    }

    public Race getRace() {
        return race;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
