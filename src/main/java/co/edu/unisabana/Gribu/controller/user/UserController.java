package co.edu.unisabana.Gribu.controller.user;

import co.edu.unisabana.Gribu.dto.ForgotPasswordUserDTO;
import co.edu.unisabana.Gribu.dto.UpdatePasswordUserDTO;
import co.edu.unisabana.Gribu.dto.UserDTO;
import co.edu.unisabana.Gribu.dto.UserDTOMapper;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/user/")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOMapper userDTOMapper;

    @PostMapping(path = "/login")
    public ResponseEntity<Optional<UserDTO>> login(@RequestBody User userLogin) {
        return new ResponseEntity<>((userService.loginId(userLogin.getUsername(), userLogin.getPassword()))
                .map(userDTOMapper), HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>("Usuario registrado con Exito", HttpStatus.CREATED);
    }
    @PutMapping(path = "/forgotPassword")
    public ResponseEntity<String> forgotUserPassword(@RequestBody ForgotPasswordUserDTO user) {
        //Email authentication
        userService.forgotPassword(user);
        return new ResponseEntity<>("Clave modificada con Exito", HttpStatus.OK);
    }
    @PutMapping(path = "/updatePassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody UpdatePasswordUserDTO user) {
        userService.updatePassword(user);
        return new ResponseEntity<>("Clave modificada con Exito", HttpStatus.OK);
    }
}
