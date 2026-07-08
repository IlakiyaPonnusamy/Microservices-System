package com.example.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.serviceImplementation.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class) // This enables Mockito in your JUnit test class.
public class EmploeeServiceTest {

	@Mock // Fake Repository
	private EmployeeRepository employeeRepo;

	@InjectMocks // Injects into Service
	private EmployeeServiceImpl employeeService;

	/*
	 * @BeforeAll // executes only once Static 
	 * static void beforeAll(){
	 * 
	 * System.out.println( "Employee Service Testing Started");
	 * 
	 * }
	 */
	
	private EmployeeRequestDto employeeDto;
	private Employee employee;

	@BeforeEach // executes before every test method prepare data, initilize object
	void setup() {

		employeeDto = new EmployeeRequestDto(); // Each test gets its own object.
		// employeeDto.setEmployeeId(101);
		employeeDto.setFirstName("Gowtham");
		employeeDto.setLastName("D");
		employeeDto.setMobileNumber("9876543210");
		employeeDto.setSalary(50000.0);
		employeeDto.setEmail("gowtham@gmail.com");
		employeeDto.setDepartment("IT");
		employeeDto.setDesignation("Engineer");
		employeeDto.setStatus("ACTIVE");

		employee = new Employee();
		// savedEmployee.setEmployeeId(101);
		employee.setFirstName("Gowtham");
		employee.setLastName("D");
		employee.setMobileNumber("9876543210");
		employee.setSalary(50000.0);
		employee.setEmail("gowtham@gmail.com");
		employee.setDepartment("IT");
		employee.setDesignation("Engineer");
		employee.setStatus("ACTIVE");

	} // every test automatically gets emloyee object ready

	/*
	 * @AfterEach // executes after every test method, cleanup test data void
	 * cleanup() {
	 * 
	 * System.out.println("Cleanup");
	 * 
	 * }
	 */

	@Test
	void testSaveEmployee() {

		when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
		// When employeeRepo.save() is called with any Employee object,return
		// savedEmployee.So no real database call happens.

		Employee result = employeeService.createEmployee(employeeDto);

		assertNotNull(result);
		assertEquals("Gowtham", result.getFirstName());
		assertEquals("IT", result.getDepartment());
		assertEquals("Engineer", result.getDesignation());
		assertEquals("ACTIVE", result.getStatus());

		verify(employeeRepo).save(any(Employee.class));

	}

	@Test
	void testDeleteEmployee() {

		when(employeeRepo.findById(1l)).thenReturn(Optional.of(employee));

		employeeService.deleteById(2l);
		verify(employeeRepo).deleteById(2l);
	}

}
