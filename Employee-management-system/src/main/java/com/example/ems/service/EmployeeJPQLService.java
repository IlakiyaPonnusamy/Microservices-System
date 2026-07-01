package com.example.ems.service;

import java.util.List;

import com.example.ems.entity.Employee;

public interface EmployeeJPQLService {

	List<Employee> getEmployeByDept(String department);

}
