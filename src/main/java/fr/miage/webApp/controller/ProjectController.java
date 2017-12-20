package fr.miage.webApp.controller;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//TODO afficher les messages d'erreur sur la pages.
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectValidator projectValidator;

    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String createProject(Model model,String errorSubject,String errorAuthor) {
        model.addAttribute("createProjectForm", new Project());
        if (errorSubject!=null) {
            model.addAttribute("errorSubject", "Sujet invalide, doit contenir un seul mot clé");
        }
        if(errorAuthor!=null){
            model.addAttribute("errorAuthor","Pseudo non existant");
        }
        return "createProject";

    }

    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("createProjectForm") Project createProjectForm, BindingResult bindingResult, Model model) {
        projectValidator.validate(createProjectForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createProject";
        }
        projectService.saveProject(createProjectForm);
        return "redirect:/welcome";
    }


}
