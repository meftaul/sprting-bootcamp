package com.mislbd.webapp.repsitory;

import com.mislbd.webapp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);
    List<Employee> findByFirstNameContaining(String firstName);

    @Query("select e from Employee e where e.firstName = ?1 or e.email = ?2")
    List<Employee> findEmployeeByFirstNameOrEmail(String firstName, String email);
}
