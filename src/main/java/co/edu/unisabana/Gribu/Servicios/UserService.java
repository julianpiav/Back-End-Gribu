package co.edu.unisabana.Gribu.Servicios;

import co.edu.unisabana.Gribu.Repositorios.UserRepository;
import co.edu.unisabana.Gribu.Entidades.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void SaveOrDelete(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public UserDTO login(String user, String password){
        User correctUser= userRepository.findByUserAndPassword(user,password);
        return new UserDTO(correctUser.getEmail(),correctUser.getName(),
                correctUser.getUsername(),correctUser.getLevel(),
                correctUser.getDayStreak(),correctUser.getAlliance(),correctUser.getUserRole());
    }

}
