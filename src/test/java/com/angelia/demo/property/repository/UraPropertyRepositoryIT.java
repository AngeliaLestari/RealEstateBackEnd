package com.angelia.demo.property.repository;

import com.angelia.demo.property.model.ura.Project;
import com.angelia.demo.property.model.ura.Result;
import com.angelia.demo.property.model.ura.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UraPropertyRepositoryIT {

    UraPropertyRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        repository = new UraPropertyRepository();
    }

    @Test
    void loadData_should_load_data_and_map_properly() throws IOException {
        // given
        final String uraTransactionFile = "/ura-property-tx-test-file.json";

        // when
        Result result = repository.loadData(uraTransactionFile);

        // then
        assertEquals("Success", result.getStatus());
        assertEquals(1, result.getResult().size());

        Project project = result.getResult().get(0);
        assertEquals("TURQUOISE", project.getProject());
        assertEquals("CCR", project.getMarketSegment());

        List<Transaction> transaction = project.getTransaction();
        assertEquals(2, transaction.size());

        Transaction txToVerify = transaction.get(0);

        assertEquals(txToVerify.getArea(), "203");
        assertEquals(txToVerify.getContractDate(), "0715");
        assertEquals(txToVerify.getPrice(), "2900000");
        assertEquals(txToVerify.getPropertyType(), "Condominium");
        assertEquals(txToVerify.getTypeOfArea(), "Strata");
        assertEquals(txToVerify.getTenure(), "99 yrs lease commencing from 2007");
        assertEquals(txToVerify.getFloorRange(), "01-05");
        assertEquals(txToVerify.getTypeOfSale(), "3");
        assertEquals(txToVerify.getDistrict(), "04");
        assertEquals(txToVerify.getNoOfUnits(), "1");


    }

    @Test
    void findAllProjects_should_return_all_loaded_projects() throws IOException {
        //given
        final String uraTransactionFile = "/ura-property-tx-test-file.json";
        repository.loadData(uraTransactionFile);
        
        //when
        List<Project> allProjects = repository.findAllProjects();

        //then
        assertEquals(1,allProjects.size());

    }
}

