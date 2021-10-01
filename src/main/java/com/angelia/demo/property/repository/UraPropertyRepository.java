package com.angelia.demo.property.repository;

import com.angelia.demo.property.model.ura.Project;
import com.angelia.demo.property.model.ura.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class UraPropertyRepository {
    private static final String FILE_BATCH1 = "/property-data/ura_residential_tx_batch1.json";
    private Result result;

    public UraPropertyRepository() throws IOException {

        this.loadData(FILE_BATCH1);

    }

    /**
     * @return all available projects and the transactions
     */
    public List<Project> findAllProjects() {
        return this.result.getResult();
    }

    /**
     * Package visibility for unit testing
     */
    Result loadData(String filepath) throws IOException {
        Resource resource = new ClassPathResource(filepath);

        ObjectMapper mapper = new ObjectMapper();

        this.result = mapper.readValue(resource.getInputStream(), Result.class);
        return this.result;
    }

}
