package com.angelia.demo.property.model.ura;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Result {

    private String status;
    private List<Project> result;

    public Result(@JsonProperty("Status") String status, @JsonProperty("Result") List<Project> result) {
        this.status = status;
        this.result = result;
    }
}
