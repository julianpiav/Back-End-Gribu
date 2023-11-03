package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.dto.UserDTO;
import co.edu.unisabana.Gribu.entity.UserRole;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.UserRepository;
import co.edu.unisabana.Gribu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserDTO> getUsers() {
        if (userRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se Encontraron Usuarios");
        }else {
            return userRepository.findAll()
                    .stream()
                    .map(user -> new UserDTO(
                            user.getEmail(),
                            user.getUsername(),
                            user.getName(),
                            user.getLevel(),
                            user.getDayStreak(),
                            user.getAlliance()
                    ))
                    .collect(Collectors.toList());
        }
    }

    public UserDTO getUserByID(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(
                        user.getEmail(),
                        user.getUsername(),
                        user.getName(),
                        user.getLevel(),
                        user.getDayStreak(),
                        user.getAlliance()
                )).orElseThrow(()-> new ResourceNotFoundException(
                        "Usuario con el ID "+id+", no encontrado."));
    }
    public void SaveOrUpdateUser(User user) {



        if (this.userExistByEmail(user.getEmail())) {
            throw  new ExistingResourceException("El email que intenta utilizar, ya esta en Uso");
        }else if (this.userExistByUsername(user.getUsername())){
            throw  new ExistingResourceException("El Usuario que intenta utilizar, ya esta en Uso");
        }else {
            String encodedPassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setUserRole(UserRole.USER);
            user.setCreationDate(ZonedDateTime.now());
            user.setUpdateDate(ZonedDateTime.now());
            user.setLevel(1);
            userRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Usuario con el ID "+id+", no existe.");
        }else {
            userRepository.deleteById(id);
        }
    }
    public Boolean login(String username, String password){
        User user= userRepository.findByUsernameAndPassword(username,password);
        return user != null;
    }
    private Boolean userExistByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user !=null;
    }
    private Boolean userExistByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user !=null;
    }


}
