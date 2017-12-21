package fr.miage.webApp.validator;

import fr.miage.webApp.model.Topic;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
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

    }

    public void validate(Object target, Errors errors,Model model) {
        Topic topic = (Topic) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "not empty");
        if (projectService.findBySubject(topic.getProjectName()) == null) {
            model.addAttribute("errorSubject", "Invalid subject");
        }
        if (userService.findByUsername(topic.getAuthor()) == null) {
            model.addAttribute("errorAuthor", "Invalide author");
        }
    }
}
