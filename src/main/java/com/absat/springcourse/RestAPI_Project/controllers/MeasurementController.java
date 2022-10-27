package com.absat.springcourse.RestAPI_Project.controllers;

import com.absat.springcourse.RestAPI_Project.dto.MeasurementDTO;
import com.absat.springcourse.RestAPI_Project.model.Measurement;
import com.absat.springcourse.RestAPI_Project.services.MeasurementsService;
import com.absat.springcourse.RestAPI_Project.services.SensorsService;
import com.absat.springcourse.RestAPI_Project.util.MeasurementErrorResponse;
import com.absat.springcourse.RestAPI_Project.util.MeasurementNotCreatedException;
import com.absat.springcourse.RestAPI_Project.util.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementsService measurementsService, SensorsService sensorsService) {
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError error: errorList) {
                errors.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errors.toString());
        }
        sensorsService.findNotFoundExceptionByName(measurementDTO.getSensor().getName());
        Measurement measurement = convertToMeasurement(measurementDTO);

        measurement.setSensor(sensorsService.findByName(measurementDTO.getSensor().getName()).orElse(null));

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getAll(){
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays(){
        return measurementsService.rainingDaysCount();
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> exceptionHandler(MeasurementNotCreatedException ex){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> exceptionHandler(SensorNotFoundException ex){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                "Sensor with such name not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement,MeasurementDTO.class);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO,Measurement.class);
    }
}
