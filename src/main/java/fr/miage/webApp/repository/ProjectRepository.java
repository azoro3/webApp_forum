package fr.miage.webApp.repository;

import fr.miage.webApp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository <Project,String> {
    Project findBySubject (String subject);
}
