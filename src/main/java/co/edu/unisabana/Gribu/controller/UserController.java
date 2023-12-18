package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.dto.UserDTO;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.exception.AuthenticationException;
import co.edu.unisabana.Gribu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserByID(id),HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.SaveOrUpdateUser(user);
        return new ResponseEntity<>("Usuario registrado con Exito", HttpStatus.CREATED);
    }

    @PostMapping(path = "/changePassword/{email}")
    public ResponseEntity<String> changePassword(@PathVariable("email") String email) {
        userService.sendPasswordChange(email);
        return new ResponseEntity<>("Entre rey", HttpStatus.CREATED);
    }

    @PostMapping(path = "/codeChangePassword/{email}/{code}")
    public ResponseEntity<String> codeChangePassword(@PathVariable("email") String email, @PathVariable("code") String code) {
        userService.confirmCodePasswordChange(email,code);
        return new ResponseEntity<>("Correo enviado", HttpStatus.CREATED);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.login(user.getUsername(), user.getPassword())){
            return new ResponseEntity<String>("Inicio de Sesión Exitoso", HttpStatus.OK);
        }else {
            throw new AuthenticationException("Inicio de sesion fallido, verifique las credenciales");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
