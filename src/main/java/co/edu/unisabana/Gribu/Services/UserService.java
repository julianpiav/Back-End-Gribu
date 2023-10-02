package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.Repositories.UserRepository;
import co.edu.unisabana.Gribu.Entities.User;
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

    public void SaveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public UserDTO login(String username, String password){
        User correctUser= userRepository.findByUsernameAndPassword(username,password);
        return new UserDTO(correctUser.getEmail(),correctUser.getName(),
                correctUser.getUsername(),correctUser.getLevel(),
                correctUser.getDayStreak(),correctUser.getAlliance(),correctUser.getUserRole());
    }

}
