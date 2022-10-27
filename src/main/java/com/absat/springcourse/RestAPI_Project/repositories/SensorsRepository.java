package com.absat.springcourse.RestAPI_Project.repositories;

import com.absat.springcourse.RestAPI_Project.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor,Integer> {
    public Optional<Sensor> findByName(String name);
}
