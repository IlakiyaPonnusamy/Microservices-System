package com.example.ems.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeJPQLRepository;
import com.example.ems.service.EmployeeJPQLService;

@Service
public class EmployeeJPQLServiceImplementation implements EmployeeJPQLService{

	private final EmployeeJPQLRepository employeeRepo;

	public EmployeeJPQLServiceImplementation(EmployeeJPQLRepository employeeRepo) {
		this.employeeRepo = employeeRepo;

	}

	@Override
	public List<Employee> getEmployeByDept(String department) {

		return employeeRepo.getEmployeesByDepartment(department);
	}
}
