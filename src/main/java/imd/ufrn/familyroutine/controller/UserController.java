package imd.ufrn.familyroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.familyroutine.model.User;
import imd.ufrn.familyroutine.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.findAll();
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return this.userService.findUserById(userId);
    }

}
