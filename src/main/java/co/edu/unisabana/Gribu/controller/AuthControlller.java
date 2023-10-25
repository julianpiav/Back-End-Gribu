package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.dto.AuthDTO;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.repository.UserRepository;
import co.edu.unisabana.Gribu.security.JWTAuthorization;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControlller {

  private JWTAuthorization jwtAuthorization;

  private UserRepository userRepository;

  @PostMapping(path = "/auth")
  public String auth(@RequestBody AuthDTO authDTO) {
    User user = userRepository.findByUsernameAndPassword(authDTO.getUser(),
        authDTO.getClave());
    if (user != null) {
      return jwtAuthorization.getJWTToken(authDTO.getUser());
    } else {
      return "Usuario o contraseña invalido";
    }
  }
}
