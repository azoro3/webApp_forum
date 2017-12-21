package fr.miage.webApp.validator;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProjectValidator implements Validator {
    private static final String NAME_PROJECT = "^([a-zA-Z]*){1}$";
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validate(Object target, Errors errors, Model model) {
        Project project = (Project) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "not empty");
        Matcher matcher = Pattern.compile(NAME_PROJECT, Pattern.CASE_INSENSITIVE).matcher(project.getSubject());
        if (!matcher.matches()) {
            model.addAttribute("errorProject", "Entrez un sujet d'un seul mot");
            errors.rejectValue("subject", "Invalid.projectForm.subject");
        }
        if (projectService.findBySubject(project.getSubject()) != null) {
            model.addAttribute("errorProject", "ce projet existe déjà");
            errors.rejectValue("subject", "Already.exist.project");

        }

        if (userService.findByUsername(project.getAuthor()) == null) {
            model.addAttribute("errorAuthor", "Cet utilisateur n'existe pas");
            errors.rejectValue("author", "Invalid.createProjectForm.username");

        }
    }
}
