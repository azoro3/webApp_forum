package fr.miage.webApp.validator;

import fr.miage.webApp.model.Topic;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TopicValidator implements Validator {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Topic topic = (Topic) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "not empty");
        if (projectService.findBySubject(topic.getProjectName()) == null) {
            errors.rejectValue("subject", "Doesnot.exist.project");
        }
        if (userService.findByUsername(topic.getAuthor()) == null) {
            errors.rejectValue("author", "Invalid.createProjectForm.username");
        }
    }
}
