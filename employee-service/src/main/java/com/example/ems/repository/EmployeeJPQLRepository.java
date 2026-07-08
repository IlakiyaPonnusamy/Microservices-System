
package com.example.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ems.entity.Employee;

@Repository
public interface EmployeeJPQLRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentIn(List<String> departments);
	List<Employee> findByDepartmentNotIn(List<String> departments);
	List<Employee> findByDepartmentIgnoreCase(String department);
	List<Employee> findByDepartmentOrderBySalaryDesc(String department);
	List<Employee> findTop3ByOrderBySalaryDesc();
	Employee findFirstByOrderBySalaryDesc();
	long countByDepartment(String department);
	boolean existsByEmail(String email);
	
	@Query("select e from Employee e where e.department =:department")
	List<Employee> getEmployeesByDepartment(@Param("department") String dptInput);
	/*
	 * e.department entity.field name, :department Placeholder(replace with
	 * input,@Param("department")JPQL Parameter Name, String department Java variable holding input
	 * ),:department and @Param("department" should use same name
	 */
	
	@Query("""
			SELECT e
					FROM Employee e
					WHERE e.department = :dept
					AND e.status = :empStatus
					""")
	List<Employee> getEmployeesByDepartmentAndStatus(@Param("dept") String departmentInput,
			@Param("empStatus") String statusInput);
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.department IN :departments
			""")
	List<Employee> getEmployeesByDepartments(@Param("departments") List<String> departmentList);
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.salary
			BETWEEN :minSalary
			AND :maxSalary
			""")
	List<Employee> getEmployeesBySalary(@Param("minSalary") Double minimumSalary,

			@Param("maxSalary") Double maximumSalary);
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.firstName
			LIKE %:searchText%
			""")
	List<Employee> searchEmployee(@Param("searchText") String searchInput);
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.salary > :salaryLimit
			""")
	List<Employee> getEmployees(@Param("salaryLimit") Double minimumSalary);
	
	@Query("""
			SELECT e
			FROM Employee e
			ORDER BY e.salary DESC
			""")
	List<Employee> getEmployees();
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.department = :dept
			ORDER BY e.salary DESC
			""")
	List<Employee> getEmployees(@Param("dept") String departmentInput);
	
	@Query("""
			SELECT e
			FROM Employee e
			WHERE e.department = :dept
			AND e.status = :status
			AND e.salary > :minimumSalary
			ORDER BY e.salary DESC
			""")
	List<Employee> getEmployees(

			@Param("dept") String departmentInput,

			@Param("status") String employeeStatus,

			@Param("minimumSalary") Double salaryLimit);
	
	@Query("SELECT COUNT(e) FROM Employee e")
	long countEmployees();
	// SUM(e.salary),AVG(e.salary),MIN(e.salary),MAX(e.salary)
	
	@Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
	List<Object[]> countEmployeesByDepartment();
	
	@Query("""
			SELECT e.department, COUNT(e)
			FROM Employee e
			GROUP BY e.department
			HAVING COUNT(e) > 5
			""")
	List<Object[]> getDepartmentsHavingMoreThanFiveEmployees();
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")
	int updateEmployeeSalary(@Param("id") Integer id, @Param("salary") Double salary);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Employee e WHERE e.department = :department")
	int deleteEmployeesByDepartment(@Param("department") String department);

	// native SQl
	@Query(value = """
	        SELECT *
	        FROM employee
	        WHERE department = :department
	        """, nativeQuery = true)
	List<Employee> getEmployee(@Param("department") String department); 
	
}
