package com.angelia.demo.property.model.ura;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Project {
    private String project;
    private String marketSegment;
    private String street;
    private String x;
    private String y;
    private List<Transaction> transaction;

    public Project(
            @JsonProperty("Project") String project,
            @JsonProperty("marketSegment") String marketSegment,
            @JsonProperty("street") String street,
            @JsonProperty("x") String x,
            @JsonProperty("y") String y,
            @JsonProperty("transaction") List<Transaction> transaction) {
        this.project = project;
        this.marketSegment = marketSegment;
        this.street = street;
        this.x = x;
        this.y = y;
        this.transaction = transaction;
    }
}

