package com.angelia.demo.property.controller;

import com.angelia.demo.property.model.ura.Project;
import com.angelia.demo.property.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SearchController {

    private final PropertyService propertyService;

    @Autowired
    public SearchController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/search/{text}")
    public List<Project> search(@PathVariable String text) {
        return propertyService.search(text);
    }

    @GetMapping("/project/{name}")
    public Project project(@PathVariable String name) {
        return propertyService.getProject(name);
    }

}

