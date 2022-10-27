package com.absat.springcourse.RestAPI_Project.services;

import com.absat.springcourse.RestAPI_Project.model.Sensor;
import com.absat.springcourse.RestAPI_Project.repositories.SensorsRepository;
import com.absat.springcourse.RestAPI_Project.util.SensorAlreadyExistException;
import com.absat.springcourse.RestAPI_Project.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Optional<Sensor> findOne(int id) {
        return sensorsRepository.findById(id);
    }
    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }

    public void findAlreadyExceptionByName(String name) {
        Optional<Sensor> sensor = sensorsRepository.findByName(name);
        if (sensor.isPresent())
            throw new SensorAlreadyExistException();
    }
    public void findNotFoundExceptionByName(String name) {
        sensorsRepository.findByName(name).orElseThrow(SensorNotFoundException::new);

    }


    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }


}
