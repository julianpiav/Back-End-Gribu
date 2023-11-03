package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.dto.AuthDTO;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.repository.UserRepository;
import co.edu.unisabana.Gribu.security.JWTAuthorization;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
public class AuthControlller {


  private JWTAuthorization jwtAuthorization;
  private UserRepository userRepository;

  @PostMapping(path = "/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthDTO authDTO) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //User user = userRepository.findByUsernameAndPassword(authDTO.getUsername(),authDTO.getPassword());
    User user = userRepository.findByUsername(authDTO.getUsername());
    if (user != null) {
      if (passwordEncoder.matches(authDTO.getPassword(), user.getPassword())){
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("token", user.getPassword() + " pasa xd"));
      }
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("token", user.getUsername() + "ci   " + user.getPassword() + "lega    " + authDTO.getPassword()));
      //return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("token", jwtAuthorization.getJWTToken(authDTO.getUsername())));
      //new ResponseEntity<>(jwtAuthorization.getJWTToken(authDTO.getUsername()), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(jwtAuthorization.getJWTToken("mal xd"), HttpStatus.CONFLICT);
    }
  }
}
