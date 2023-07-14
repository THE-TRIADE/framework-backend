package imd.ufrn.framework.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.model.api.request.LoginRequest;
import imd.ufrn.framework.model.api.request.UserRequest;
import imd.ufrn.framework.model.api.response.UserResponse;
import imd.ufrn.framework.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam Optional<String> role) {
        return
        role.map(userRole -> {
            return this.userService.findAllByRole(userRole);
        })
        .orElse(this.userService.findAll());
    }

    @GetMapping("{userId}")
    public UserResponse findUserById(@PathVariable Long userId) {
        return this.userService.findUserByUserId(userId);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest newUser) {
        return this.userService.createUser(newUser);
    }

    @PostMapping("/login")
    public UserResponse logInUser(@RequestBody @Valid LoginRequest loginRequest) {
        return this.userService.authenticateUser(loginRequest);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        this.userService.deleteAllUsers();
    }

    @DeleteMapping("{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
    } 
}
