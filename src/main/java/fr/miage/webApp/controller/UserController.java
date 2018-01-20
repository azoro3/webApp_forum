package fr.miage.webApp.controller;

import fr.miage.webApp.model.User;
import fr.miage.webApp.service.SecurityService;
import fr.miage.webApp.service.UserService;
import fr.miage.webApp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.saveUser(userForm);

        // Auto-login the new user
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Locale locale, Model model, String error, String logout) {

        String errorMessage = messageSource.getMessage("login.invalid", new Object[]{}, locale);
        String logoutMessage = messageSource.getMessage("logout.message", new Object[]{}, locale);

        if (error != null) {
            model.addAttribute("error", errorMessage);
        }

        if (logout != null) {
            model.addAttribute("message", logoutMessage);
        }

        return "login";
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public String showUser(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "showUser";
    }
}
