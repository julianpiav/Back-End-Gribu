package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.dto.UserDTO;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.exception.AuthenticationException;
import co.edu.unisabana.Gribu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
    @GetMapping("/{id}/loggedDays")
    public ResponseEntity<Set<DayOfWeek>> getLoggedDaysInCurrentWeek(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getLoggedDaysInCurrentWeek(id),HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("Usuario registrado con Exito", HttpStatus.CREATED);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>("Usuario modificado con Exito", HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody User userLogin) {
        if (userService.login(userLogin.getUsername(), userLogin.getPassword())!=null){
            userService.loginDay(userService.login(userLogin.getUsername(), userLogin.getPassword()).getId());
            return new ResponseEntity<>("Inicio de Sesión Exitoso", HttpStatus.OK);
        }else {
            throw new AuthenticationException("Inicio de sesion fallido, verifique las credenciales");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("El usuario con id= "+id+" ha sido borrado",HttpStatus.OK);
    }
}
