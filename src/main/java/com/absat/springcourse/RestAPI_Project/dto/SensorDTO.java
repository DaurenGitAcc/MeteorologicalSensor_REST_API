package com.absat.springcourse.RestAPI_Project.dto;

import com.absat.springcourse.RestAPI_Project.model.Measurement;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class SensorDTO {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
