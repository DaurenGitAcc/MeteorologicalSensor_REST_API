package com.absat.springcourse.RestAPI_Project.dto;

import com.absat.springcourse.RestAPI_Project.model.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MeasurementDTO {

    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100,max = 100,message = "Value should be between -100 and 100")
    private float value;

    @NotEmpty(message = "\"Raining\" parameter should not be empty")
    private boolean raining;

    @NotEmpty(message = "Sensor' name should not be empty")
    @NotNull(message = "Sensor' name should not be empty")
    private SensorDTO sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
