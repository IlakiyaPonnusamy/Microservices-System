package com.example.ems;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ems.config.SecurityConfig;
import com.example.ems.controller.EmployeeController;
import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.entity.Employee;
import com.example.ems.security.JwtAuthFilter;
import com.example.ems.security.JwtService;
import com.example.ems.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = EmployeeController.class, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { JwtAuthFilter.class,
				SecurityConfig.class }) }) //Loads only the controller layer and excluding filter
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {

	@Autowired //It is used to send fake HTTP requests.
    private MockMvc mockMvc;

    @MockitoBean //Creates fake EmployeeService and injects it into controller.
    private EmployeeService employeeService;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void testCreateEmployee() throws Exception {

    	//request dto
        EmployeeRequestDto request = new EmployeeRequestDto();
        //request.setEmployeeId(101);
        request.setFirstName("Gowtham");
        request.setLastName("D");
        request.setMobileNumber("9876543210");
        request.setSalary(50000.0);
        request.setEmail("gowtham@gmail.com");
        request.setDepartment("IT");
        request.setDesignation("Engineer");
        request.setStatus("ACTIVE");

        //response employee
        Employee response = new Employee();
        //response.setEmployeeId(101);
        response.setFirstName("Gowtham");
        response.setLastName("D");
        response.setMobileNumber("9876543210");
        response.setSalary(50000.0);
        response.setEmail("gowtham@gmail.com");
        response.setDepartment("IT");
        response.setDesignation("Engineer");
        response.setStatus("ACTIVE");

        when(employeeService.createEmployee(any(EmployeeRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/employees")  //POST API call.
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) //Converts EmployeeRequestDto into JSON request body.
                .andExpect(status().isOk()) //Checks HTTP status is 200 OK.
                .andExpect(jsonPath("$.employeeId").value(101))  //Checks response JSON field value.
                .andExpect(jsonPath("$.firstName").value("Gowtham"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.designation").value("Engineer"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
}