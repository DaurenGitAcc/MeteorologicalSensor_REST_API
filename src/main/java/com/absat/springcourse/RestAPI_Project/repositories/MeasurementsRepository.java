package com.absat.springcourse.RestAPI_Project.repositories;

import com.absat.springcourse.RestAPI_Project.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement,Integer> {
    int countAllByRainingIsTrue();
}
