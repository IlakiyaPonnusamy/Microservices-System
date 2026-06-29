package com.example.ems.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.entity.Employee;
import com.example.ems.exception.EmployeeNotFoundException;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// constructor injection
	private final EmployeeRepository employeeRepo;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee createEmployee(EmployeeRequestDto request) {
		Employee emp = new Employee();
		emp.setEmployeeId(request.getEmployeeId());
		emp.setFirstName(request.getFirstName());
		emp.setLastName(request.getLastName());
		emp.setMobileNumber(request.getMobileNumber());
		emp.setSalary(request.getSalary());
		emp.setEmail(request.getEmail());
		emp.setDepartment(request.getDepartment());
		emp.setDateOfJoining(request.getDateOfJoining());
		emp.setDesignation(request.getDesignation());
		emp.setStatus(request.getStatus());

		return employeeRepo.save(emp);

	}

	@Override
	public Optional<Employee> getEmployebyId(Long id) {

		Optional<Employee> employee = employeeRepo.findById(id);
		try {
			// if (employee.isEmpty()) // Java 11+
			if (!employee.isPresent()) {

				throw new EmployeeNotFoundException("Employee not found with id : " + id);

			}
			
			 System.out.println("Employee Found");

		} catch (EmployeeNotFoundException e) {

			System.out.println(e.getMessage());

		} finally {
			System.out.println("Employee : " + id);
		}
		return employee;

		/*
		 * return employeeRepo.findById(id) .orElseThrow(() -> new
		 * EmployeeNotFoundException( "Employee not found with id : " + id));
		 */

	}
	

	@Override 
	public List<Employee> getEmpList() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee updateEmployee(Long id, EmployeeRequestDto request) {

		Employee emp = employeeRepo.findById(id).orElse(null);
		emp.setEmployeeId(request.getEmployeeId());
		emp.setFirstName(request.getFirstName());
		emp.setLastName(request.getLastName());
		emp.setMobileNumber(request.getMobileNumber());
		emp.setSalary(request.getSalary());
		emp.setEmail(request.getEmail());
		emp.setDepartment(request.getDepartment());
		emp.setDateOfJoining(request.getDateOfJoining());
		emp.setDesignation(request.getDesignation());
		emp.setStatus(request.getStatus());
		return employeeRepo.save(emp);
	}

	@Override
	public Employee updateEmployeeLastname(Long id, String input) {

		return employeeRepo.findById(id).map(employee -> {
			employee.setLastName(input);
			return employeeRepo.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));
		// .orElse(null);

	}

	@Override
	public void deleteById(Long id) {
		if (!employeeRepo.existsById(id)) {
			return;
		}

		employeeRepo.deleteById(id);
	}

}
