package com.example.ems.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeRequestDto;
import com.example.ems.dto.EmployeeResponseDto;
import com.example.ems.entity.Employee;
import com.example.ems.exception.EmployeeNotFoundException;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// constructor injection
	private final EmployeeRepository employeeRepo;
	private final DepartmentValidationService departmentValidationService;

	public EmployeeServiceImpl(EmployeeRepository employeeRepo,
			DepartmentValidationService departmentValidationService) {
		this.employeeRepo = employeeRepo;
		this.departmentValidationService = departmentValidationService;
	}

	@Override
	@Transactional
	public Employee createEmployee(EmployeeRequestDto request) {

		//validating department id feign cal
		departmentValidationService.validateDepartment(request.getDepartmentId());
		
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
		emp.setDepartmentId(request.getDepartmentId());

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
		emp.setDepartmentId(request.getDepartmentId());
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
	//@PreAuthorize("hasRole('ADMIN')") // only admin role user can delete 
	public void deleteById(Long id) {
		if (!employeeRepo.existsById(id)) {
			return;
		}

		employeeRepo.deleteById(id);
	}

	@Override
	public Page<Employee> fetchEmployee(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return employeeRepo.findAll(pageable);
		
		/*
		 * Pageable pageable =PageRequest.of( page,size,Sort.by("salary").descending()); 
		 * Sort.by("salary").ascending(); orSort.by("salary"); 
		 * Sort sort = Sort.by("department").ascending() .and(
		 *                       Sort.by("salary") .descending());
		 */
		
		/*
		 * Sort sort = direction.equalsIgnoreCase("asc")
		 * 
		 * ?Sort.by(sortBy).ascending()
		 * 
		 * :Sort.by(sortBy).descending();
		 */
	}

	@Override
	public List<EmployeeResponseDto> getEmployeesByDepartmentId(Long departmentId) {

		return employeeRepo.findByDepartmentId(departmentId).stream()
				.map(employee -> new EmployeeResponseDto(employee.getId(), employee.getFirstName(),
						employee.getLastName(), employee.getDesignation(), employee.getSalary(), employee.getEmail(),
						employee.getMobileNumber(), employee.getDepartment(), employee.getDateOfJoining(),
						employee.getStatus(), employee.getDepartmentId()))
				.toList();
	}

}
