package fr.miage.webApp.service;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public void saveProject(Project project) {
        project.setId(UUID.randomUUID().toString());
        projectRepository.save(project);
    }
    public Project findBySubject (String subject){
        return projectRepository.findBySubject(subject);
    }
}
