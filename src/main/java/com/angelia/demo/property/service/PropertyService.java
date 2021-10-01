package com.angelia.demo.property.service;

import com.angelia.demo.property.model.ura.Project;
import com.angelia.demo.property.repository.UraPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final UraPropertyRepository uraPropertyRepository;

    @Autowired
    public PropertyService(UraPropertyRepository uraPropertyRepository) {
        this.uraPropertyRepository = uraPropertyRepository;
    }

    /**
     *
     * @param text to search (it can be partially completed and in-case-sensitive
     *
     * @return projects where the street name partially or fully match with the text
     */
    public List<Project> search(String text) {
        final String lowerCaseText = text.toLowerCase();

        return uraPropertyRepository
                .findAllProjects()
                .stream()
                .filter(project -> project.getStreet().toLowerCase().contains(lowerCaseText))
                .limit(30)
                .collect(Collectors.toList());
    }

    /**
     * @param name project name to retrieve (in-case-sensitive)
     * @return the project
     */
    public Project getProject(String name) {
        final String lowerCase = name.toLowerCase();

        return uraPropertyRepository
                .findAllProjects()
                .stream()
                .filter(project -> project.getProject().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null)
                ;
    }

}
