package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.DTO.UserDTO;
import co.edu.unisabana.Gribu.Exceptions.ResourceNotFoundException;
import co.edu.unisabana.Gribu.Repositories.UserRepository;
import co.edu.unisabana.Gribu.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getUsers() {
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
        userRepository.save(user);
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
    public Boolean userExistByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user !=null;
    }
    public Boolean userExistByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user !=null;
    }
}
