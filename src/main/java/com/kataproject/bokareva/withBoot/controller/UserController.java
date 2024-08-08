package com.kataproject.bokareva.withBoot.controller;

import com.kataproject.bokareva.withBoot.model.User;
import com.kataproject.bokareva.withBoot.service.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = (Logger) LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        logger.debug("Fetching all users");
        model.addAttribute("users", userService.getAllUsers());
        return "showAllUsers";
    }

    @GetMapping("/view")
    public String showUser(@RequestParam("id") long id, Model model) {
        logger.debug("Fetching user with id: {}", id);
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            return "redirect:/users";
        }

        return "showUser";
    }


    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        logger.debug("Showing new user form");
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors occurred while creating user: {}", bindingResult.getAllErrors());
            return "new";
        }
        logger.debug("Creating new user: {}", user);
        userService.save(user);
        return "redirect:/users";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model) {
        logger.debug("Fetching user for editing with id: {}", id);
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            return "redirect:/users";
        }

        return "edit";
    }



    @GetMapping("/update")
    public String updateForm(@RequestParam("id") long id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            return "redirect:/users";
        }

        return "edit";
    }


    @PostMapping("/update")
    public String update(@RequestParam("id") long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors occurred while updating user: {}", bindingResult.getAllErrors());
            return "edit";
        }
        logger.debug("Updating user: {}", user);
        userService.updateUser(id, user);
        return "redirect:/users";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String redirectToShowUser(@PathVariable("id") long id) {
        logger.debug("Redirecting to show user with id: {}", id);
        return "redirect:/users/view?id=" + id;
    }

    @GetMapping("/{id}/edit")
    public String redirectToEditUser(@PathVariable("id") long id) {
        logger.debug("Redirecting to edit user with id: {}", id);
        return "redirect:/users/edit?id=" + id;
    }
}