package fr.miage.webApp.controller;

import fr.miage.webApp.model.Project;
import fr.miage.webApp.model.Topic;
import fr.miage.webApp.service.ProjectService;
import fr.miage.webApp.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO afficher les messages d'erreur sur la pages.
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectValidator projectValidator;

    /*@RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String createProject(Model model) {
        model.addAttribute("createProjectForm", new Project());
        return "createProject";

    }*/

    /*@RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("createProjectForm") Project createProjectForm, BindingResult bindingResult, Model model) {
        projectValidator.validate(createProjectForm, bindingResult,model);
        if (bindingResult.hasErrors()) {
            return "createProject";
        }
        projectService.saveProject(createProjectForm);
        return "redirect:/welcome";
    }*/

    @RequestMapping(value = {"/", "/welcome", "/projects"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "welcome";
    }

    @RequestMapping(value = "/projects/{projectSubject}", method = RequestMethod.GET)
    public String topicList(Model model, @PathVariable("projectSubject") String subject) {
        Project project = projectService.findBySubject(subject);
        if(project != null){
            model.addAttribute("project", project);
            model.addAttribute("topics", project.getTopics());
            return "topicGrid";
        }else{
            return "redirect:/projects";
        }
    }

    @RequestMapping(value =  {"/", "/welcome", "/projects"}, method = RequestMethod.POST)
    public String createProject(Model model,
                                @ModelAttribute("createProjectForm") Project project,
                                BindingResult bindingResult) {
        projectValidator.validate(project, bindingResult,model);
        if (!bindingResult.hasErrors()) {
            projectService.saveProject(project);
        }

        return "redirect:/projects";
    }
}
