package com.hackathon.classeafieds.web.controller;

import com.hackathon.classeafieds.model.User;
import com.hackathon.classeafieds.service.UserService;
import com.hackathon.classeafieds.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    //Login page
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");
        } catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }
        return "user/login";
    }

    //Logout page
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logoutForm(HttpServletResponse response, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    // Register Page
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        if(!model.containsAttribute("user"))
            model.addAttribute("user", new User());

        model.addAttribute("action", "/register");
        model.addAttribute("submit", "Register");
        return "user/register";
    }

    // Register a new USER
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            //Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);

            //Add the User if invalid data was recieved
            redirectAttributes.addFlashAttribute("user", user);

            //Redirect back to the form
            return "redirect:/register";
        }
        userService.save(user);

        //Add flash message for success
        redirectAttributes.addFlashAttribute("flashRegister", new FlashMessage("You successfully registered! \n Please Login!", FlashMessage.Status.SUCCESS));

        return "redirect:/login";
    }

}
