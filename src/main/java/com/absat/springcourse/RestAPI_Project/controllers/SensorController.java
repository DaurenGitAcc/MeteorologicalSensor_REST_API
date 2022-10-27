package com.absat.springcourse.RestAPI_Project.controllers;

import com.absat.springcourse.RestAPI_Project.dto.SensorDTO;
import com.absat.springcourse.RestAPI_Project.model.Sensor;
import com.absat.springcourse.RestAPI_Project.services.SensorsService;
import com.absat.springcourse.RestAPI_Project.util.SensorAlreadyExistException;
import com.absat.springcourse.RestAPI_Project.util.SensorErrorResponse;
import com.absat.springcourse.RestAPI_Project.util.SensorNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorsService sensorsService, ModelMapper mapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = mapper;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                stringBuilder.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(stringBuilder.toString());
        }
        sensorsService.findAlreadyExceptionByName(sensorDTO.getName());
        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handlerException(SensorAlreadyExistException ex){
        SensorErrorResponse response = new SensorErrorResponse("Sensor with such name already exists"
                ,System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<SensorErrorResponse> handlerException(SensorNotCreatedException ex){
        SensorErrorResponse response = new SensorErrorResponse(ex.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }
    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor,SensorDTO.class);
    }

}
