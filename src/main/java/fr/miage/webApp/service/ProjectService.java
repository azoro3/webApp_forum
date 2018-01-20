package fr.miage.webApp.service;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Method to persist project in database
     *
     * @param project Project to save
     */
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    /**
     * @param subject Name of the project to find
     * @return The subject or null
     */
    public Project findBySubject(String subject) {
        return projectRepository.findBySubject(subject);
    }

    /**
     * @return All subject
     */
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
