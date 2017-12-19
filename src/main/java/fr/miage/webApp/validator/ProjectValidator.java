package fr.miage.webApp.validator;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.xml.soap.Name;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProjectValidator implements Validator {
    @Autowired
    private ProjectService projectService;
    private static final String NAME_PROJECT="^([a-zA-Z]*){1}$";
    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project =(Project) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"subject","not empty");
        Matcher matcher = Pattern.compile(NAME_PROJECT,Pattern.CASE_INSENSITIVE).matcher(project.getSubject());
        if (!matcher.matches()){
            errors.rejectValue("subject","Invalid.projectForm.subject");
        }

    }
}