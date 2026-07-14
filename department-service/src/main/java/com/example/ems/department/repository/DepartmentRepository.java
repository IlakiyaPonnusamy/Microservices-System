package com.example.ems.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import com.example.ems.department.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	//derived query
	List<Department> findByLocationContainingIgnoreCase(String param);

	// jpql query
	@Query("""
			SELECT d
			FROM Department d
			WHERE d.name = :name
			AND d.active = :active
			""")
	List<Department> findByNameAndStatus(@Param("name") String name, @Param("active") Boolean active);
	
	@Query("""
			SELECT d
			FROM Department d
			WHERE d.location IN :locations
			""")
	List<Department> findByLocations(@Param("locations") List<String> locations);
	
	@Query("""
			SELECT d
			FROM Department d
			ORDER BY d.name ASC
			""")
	List<Department> findAllOrderByName();
	
	@Modifying
	@Query("""
			UPDATE Department d
			SET d.active = :active
			WHERE d.id = :id
			""")
	int updateDepartmentStatus(@Param("id") Long id, @Param("active") Boolean active);

}
