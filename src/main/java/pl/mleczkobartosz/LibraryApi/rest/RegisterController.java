package pl.mleczkobartosz.LibraryApi.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mleczkobartosz.LibraryApi.Entity.User;
import pl.mleczkobartosz.LibraryApi.service.UserService;

import javax.validation.Valid;

@RestController
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

}
