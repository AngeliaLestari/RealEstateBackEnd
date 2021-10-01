package com.angelia.demo.property.service;

import com.angelia.demo.property.model.ura.Project;
import com.angelia.demo.property.repository.UraPropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {
    PropertyService service;

    @Mock
    UraPropertyRepository mockRepository;

    @BeforeEach
    void setUp() {
        this.service = new PropertyService(mockRepository);
    }

    @Test
    void search_should_return_projects_where_streetname_contains_the_searchtext_incasesensitive() {
        // given
        String searchText = "kampong";
        List<Project> projectList = Arrays.asList(
                Project.builder()
                        .project("Alexandra Point")
                        .street("ALEXANDRA ROAD")
                        .build(),
                Project.builder()
                        .project("TURQUOISE")
                        .street("Kampong Bahru")
                        .build(),
                Project.builder()
                        .project("MAMAT")
                        .street("KAMPONG MAMAT")
                        .build()
                );
        when(mockRepository.findAllProjects()).thenReturn(projectList);

        // when
        List<Project> searchResult = service.search(searchText);

        // then
        assertEquals(2, searchResult.size());
        assertEquals("TURQUOISE", searchResult.get(0).getProject());
        assertEquals("MAMAT", searchResult.get(1).getProject());
    }

    @Test
    void search_should_limit_the_result_to_the_first_30() {
        // given
        List<Project> projectList = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            projectList.add(
                    Project.builder()
                        .project("MAMAT " + i)
                        .street("KAMPONG " + i)
                        .build()
                    );
        }
        projectList.add(Project.builder()
                .project("Alexandra Point")
                .street("ALEXANDRA ROAD")
                .build());
        when(mockRepository.findAllProjects()).thenReturn(projectList);

        String searchText = "kam";

        // when
        List<Project> searchResult = service.search(searchText);

        // then
        assertEquals(30, searchResult.size());
    }

    @Test
    void getProject_should_return_project_by_the_name_incasesensitive() {
        // given
        List<Project> projectList = Arrays.asList(
                Project.builder()
                        .project("Alexandra Point")
                        .street("ALEXANDRA ROAD")
                        .build(),
                Project.builder()
                        .project("TURQUOISE")
                        .street("Kampong Bahru")
                        .build(),
                Project.builder()
                        .project("MAMAT")
                        .street("KAMPONG MAMAT")
                        .build()
        );
        when(mockRepository.findAllProjects()).thenReturn(projectList);

        String projectName = "mamat";

        // when
        Project result = service.getProject(projectName);

        // then
        assertNotNull(result);
        assertEquals("MAMAT", result.getProject());
    }

    @Test
    void getProject_should_return_null_if_not_found() {
        // given
        List<Project> projectList = Arrays.asList(
                Project.builder()
                        .project("Alexandra Point")
                        .street("ALEXANDRA ROAD")
                        .build(),
                Project.builder()
                        .project("TURQUOISE")
                        .street("Kampong Bahru")
                        .build(),
                Project.builder()
                        .project("MAMAT")
                        .street("KAMPONG MAMAT")
                        .build()
        );
        when(mockRepository.findAllProjects()).thenReturn(projectList);

        String projectName = "abcd";

        // when
        Project result = service.getProject(projectName);

        // then
        assertNull(result);
    }

}