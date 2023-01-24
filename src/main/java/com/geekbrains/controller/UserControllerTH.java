package com.geekbrains.controller;

import com.geekbrains.model.Role;
import com.geekbrains.model.User;
import com.geekbrains.service.RoleService;
import com.geekbrains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserControllerTH {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String showUserPage(Model model) {
        List<User> userList = userService.getAllUsers();
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("userList", userList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("newUser", new User());
        model.addAttribute("oldUser", new User());
        return "users";
    }


    @GetMapping("/update_form/{id}")
    public String showUserPageAndFillForm(@PathVariable Long id, Model model) {
        List<User> userList = userService.getAllUsers();
        User user = userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("userList", userList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("newUser", new User());
        model.addAttribute("oldUser", user);
        return "users";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/add")
    public String AddUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
