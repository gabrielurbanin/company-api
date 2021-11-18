package br.com.hotmart.java.repositories;

import br.com.hotmart.java.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByName(String name);

    List<Employee> findAllBySupervisorId(Long id);

    List<Employee> findAllByProjectsDepartmentId(Long id);
}
