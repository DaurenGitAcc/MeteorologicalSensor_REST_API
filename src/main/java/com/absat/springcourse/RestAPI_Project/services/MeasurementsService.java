package com.absat.springcourse.RestAPI_Project.services;

import com.absat.springcourse.RestAPI_Project.model.Measurement;
import com.absat.springcourse.RestAPI_Project.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }
    public Optional<Measurement> findOne(int id){
        return measurementsRepository.findById(id);
    }

    @Transactional
    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }

    public int rainingDaysCount(){
        return measurementsRepository.countAllByRainingIsTrue();
    }
}
