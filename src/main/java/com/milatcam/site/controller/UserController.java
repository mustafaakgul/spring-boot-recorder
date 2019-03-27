package com.milatcam.site.controller;

import com.milatcam.site.model.UserAddDTO;
import com.milatcam.site.service.UserService;
import com.milatcam.site.validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private  RegisterValidator registerValidator;


    /**@Autowired
    public UserController(UserService userService, RegisterValidator registerValidator) {
        this.userService = userService;
        this.registerValidator = registerValidator;
    }*/

    /**@InitBinder()
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerValidator);
    }*/

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {

        return new ModelAndView("register", "userForm", new UserAddDTO());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("userForm") UserAddDTO form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userService.addUser(form);
        return "redirect:/users";
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {

        return new ModelAndView("users", "users", userService.getUsers());
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        userService.deleteItemById(id);
        return "redirect:/users";
    }
}
