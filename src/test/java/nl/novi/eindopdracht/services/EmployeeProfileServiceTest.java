package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.mappers.EmployeeProfileDtoMapper;
import nl.novi.eindopdracht.repositories.EmployeeProfileRepository;
import nl.novi.eindopdracht.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeProfileServiceTest {

    @Mock
    private EmployeeProfileRepository employeeProfileRepository;

    @Mock
    private EmployeeProfileDtoMapper employeeProfileDtoMapper;

    @Mock
    private PersonRepository personRepository;

    private EmployeeProfileService employeeProfileService;

    @BeforeEach
    void setUp() {

        employeeProfileService =
                new EmployeeProfileService(
                        employeeProfileRepository,
                        employeeProfileDtoMapper,
                        personRepository);
    }

    @Test
    void contextLoads() {

    }

}