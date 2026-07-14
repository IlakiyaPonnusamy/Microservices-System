package com.example.ems.department.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ems.department.dto.DepartmentRequestDto;
import com.example.ems.department.dto.DepartmentResponseDto;
import com.example.ems.department.entity.Department;
import com.example.ems.department.exception.DepartmentNotFoundException;
import com.example.ems.department.repository.DepartmentRepository;
import com.example.ems.department.service.DepartmentService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepo;

	public DepartmentServiceImpl(DepartmentRepository departmentRepo) {
		this.departmentRepo = departmentRepo;

	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();

	}

	@Override
	public Department createDepartment(DepartmentRequestDto request) {
		Department department = new Department();
		department.setName(request.getName());
		department.setCode(request.getCode());
		department.setLocation(request.getLocation());
		department.setActive(request.getActive());
		return departmentRepo.save(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentRepo.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found with id : " + id));

	}

	@Override
	public void deleteById(Long id) {
		if (!departmentRepo.existsById(id)) {
			throw new DepartmentNotFoundException("Department not found with id: " + id);
		}

		departmentRepo.deleteById(id);

	}

	@Override
	public List<DepartmentResponseDto> getActiveDepartments() {
		return departmentRepo.findAll().stream().filter(d -> d.getActive().equals(Boolean.TRUE))
				.map(d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(), d.getActive())).toList();

		// List<Department> findByActiveTrue(); using derived query
	}

	@Override
	public List<DepartmentResponseDto> searchByLocation(String param) {
		List<Department> deptList = departmentRepo.findByLocationContainingIgnoreCase(param);

		// List<Department> deptList = departmentRepo.findByLocationContaining(param);
		// both will return same as sql is case sensitive

		return deptList.stream()
				.map(d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(), d.getActive())).toList();

	}

	@Override
	public List<DepartmentResponseDto> sortingByFields() {

		List<Department> deptList = departmentRepo.findAll();
		return deptList.stream().sorted(Comparator.comparing(Department::getName))
				.map(d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(), d.getActive()))
				.collect(Collectors.toList());

		// .reversed to get descending
		// .sorted(Comparator.comparing(Department::getName).thenComparing(Department::getLocation))
		// -> to sort multiple fields

		// long count = deptList.stream().filter(d->
		// d.getActive().equals(Boolean.TRUE)).count();
		// boolean allActive =
		// departmentRepository.findAll().stream().allMatch(department
		// ->Boolean.TRUE.equals(department.getActive()));
		// allmatch(),anyMatch(), noneMatch() -> returns boolean
		// .distinct(), .findFirst(), .findAny()
	}

	@Override
	public List<DepartmentResponseDto> fetchUsingJPQl(String name, Boolean active) {

		List<Department> deptList = departmentRepo.findByNameAndStatus(name, active);

		return deptList.stream()
				.map(d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(), d.getActive())).toList();
	}

	@Override
	public Map<String, List<DepartmentResponseDto>> groupDepartments() {

		List<Department> deptList = departmentRepo.findAll();
		return deptList.stream()
				.collect(Collectors.groupingBy(Department::getLocation, Collectors.mapping(
						d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(), d.getActive()),
						Collectors.toList())));

		// Collectors.groupingBy(Department::getLocation,Collectors.counting()) ->return map<String, Long>
		// .collect(Collectors.partitioningBy(d -> Boolean.TRUE.equals(d.getActive()),
		// Collectors.mapping(d -> new DepartmentResponseDto(d.getName(), d.getCode(), d.getLocation(),d.getActive()),Collectors.toList())));
		//.collect(Collectors.joining(", ")); -> Combines multiple strings into one string using a delimiter  IT, HR, Finance

	}

}
