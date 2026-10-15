package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.repositories.EmployeeProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class EmployeeProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeProfileRepository employeeProfileRepository;

    @Test
    @WithMockUser(roles = "USER")
    void getEmployeeProfileById_shouldReturnEmployeeProfile_WhenEmployeeProfileExists() throws Exception {

        mockMvc.perform(get("/employee-profiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.personId").value(1))
                .andExpect(jsonPath("$.driversLicense").value("AUTO"));
    }
}
