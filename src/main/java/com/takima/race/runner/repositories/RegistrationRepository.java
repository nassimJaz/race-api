package com.takima.race.runner.repositories;

import com.takima.race.runner.entities.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findRegistrationByRaceId(Long raceId);
    List<Registration> findRegistrationByRunnerId(Long runnerId);
    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);
    long countByRaceId(Long raceId);
}
