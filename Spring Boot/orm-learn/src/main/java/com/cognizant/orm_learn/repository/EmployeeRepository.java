package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("FROM Employee e WHERE e.permanent = true")
    List<Employee> getPermanentEmployees();
    @Query("SELECT DISTINCT e FROM Employee e "
     + "LEFT JOIN FETCH e.department "
     + "LEFT JOIN FETCH e.skillList "
     + "WHERE e.permanent = true")
List<Employee> getPermanentEmployeesJoinFetch();
@Query(value = "SELECT * FROM employee WHERE em_permanent = true", nativeQuery = true)
List<Employee> getPermanentEmployeesNative();
@Query("SELECT AVG(e.salary) FROM Employee e")
double getAverageSalary();

@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
double getAverageSalary(@Param("id") int id);

}