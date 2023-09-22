package co.edu.unisabana.Gribu.Controladores;

import co.edu.unisabana.Gribu.Servicios.UserDTO;
import co.edu.unisabana.Gribu.Servicios.UserService;
import co.edu.unisabana.Gribu.Entidades.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{Id}")
    public Optional<User> getUser(@PathVariable("Id") Long Id) {
        return userService.getUser(Id);
    }

    @PostMapping(path = "/update")
    public User SaveOrDelete(@RequestBody User user) {
        userService.SaveOrDelete(user);
        return user;
    }
    @PostMapping(path = "/login")
    public UserDTO login(@RequestBody User user) {
        return userService.login(user.getUsername(),user.getPassword());

    }

    @DeleteMapping("/delete/{Id}")
    public void deleteUser(@PathVariable("Id") Long Id) {
        userService.delete(Id);
    }

}