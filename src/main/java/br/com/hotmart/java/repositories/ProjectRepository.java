package br.com.hotmart.java.repositories;

import br.com.hotmart.java.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByEmployeesId(Long id);

    List<Project> findAllByDepartmentId(Long id);
}
