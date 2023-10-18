package co.edu.unisabana.Gribu.Controllers;

import co.edu.unisabana.Gribu.Entities.User;
import co.edu.unisabana.Gribu.Entities.UserRole;
import co.edu.unisabana.Gribu.Exceptions.AuthenticationException;
import co.edu.unisabana.Gribu.Exceptions.ExistingUserException;
import co.edu.unisabana.Gribu.Exceptions.ResourceNotFoundException;
import co.edu.unisabana.Gribu.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getUsers() {
        if (userService.getUsers().isEmpty()){
            throw new ResourceNotFoundException("No se Encontraron Usuarios");
        }
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{Id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("Id") Long Id) {
        if (userService.getUserByID(Id).isEmpty()){
            throw new ResourceNotFoundException("Usuario no encontrado con ese id");
        }
        return new ResponseEntity<>(userService.getUserByID(Id),HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        if (userService.userExistByEmail(user.getEmail())) {
            throw  new ExistingUserException("El email que intenta utilizar, ya esta en Uso");
        }else if (userService.userExistByUsername(user.getUsername())){
            throw  new ExistingUserException("El Usuario que intenta utilizar, ya esta en Uso");
        }else{
            user.setUserRole(UserRole.USER);
            user.setCreationDate(ZonedDateTime.now());
            user.setUpdateDate(ZonedDateTime.now());
            user.setLevel(1);
            userService.SaveOrUpdateUser(user);
            return new ResponseEntity<>("Usuario registrado con Exito", HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.login(user.getUsername(), user.getPassword())){
            return new ResponseEntity<>("Inicio de Sesión Exitoso", HttpStatus.OK);
        }else {
            throw new AuthenticationException("Inicio de sesion fallido, verifique las credenciales");
        }

    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("Id") Long Id) {
        if (userService.getUserByID(Id).isEmpty()){
            throw new ResourceNotFoundException("Usuario no encontrado con ese id");
        }
        userService.deleteUser(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
