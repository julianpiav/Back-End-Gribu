package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.DTO.UserRequest;
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

    public Optional<User> getUserByID(Long id){
        return userRepository.findById(id);
    }
    public void SaveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
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
