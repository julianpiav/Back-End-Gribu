package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.dto.ForgotPasswordUserDTO;
import co.edu.unisabana.Gribu.dto.UpdatePasswordUserDTO;
import co.edu.unisabana.Gribu.entity.UserRole;
import co.edu.unisabana.Gribu.exception.AuthenticationException;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.UserRepository;
import co.edu.unisabana.Gribu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        if (userRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se Encontraron Usuarios");
        }else {
            return userRepository.findAll();
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow((()-> new ResourceNotFoundException(
                        "Usuario con el ID "+id+", no encontrado.")));
    }
    public void registerUser(User user) {
        if (this.getUserByUsername(user.getUsername()) != null) {
            throw new ExistingResourceException("El usuario que intenta utilizar, ya esta en Uso");
        } else if (this.getUserByEmail(user.getEmail()) != null) {
            throw new ExistingResourceException("El email que intenta utilizar, ya esta en Uso");
        } else {
            user.setUserRole(UserRole.USER);
            user.setCreationDate(ZonedDateTime.now());
            user.setUpdateDate(ZonedDateTime.now());
            user.setLevel(1);
            userRepository.save(user);
        }
    }
    public void updateUser(User user) {
        if (this.getUserById(user.getId())==null) {
            throw new ResourceNotFoundException("El usuario que intenta modificar no existe");
        }else {
            user.setUpdateDate(ZonedDateTime.now());
            userRepository.save(user);
        }
    }
    public void updatePassword(UpdatePasswordUserDTO userDTO) {
        if (this.getUserById(userDTO.id())==null) {
            throw new ResourceNotFoundException("El usuario que intenta modificar no existe");
        }else {
            if (userDTO.newPassword().equals(this.getUserById(userDTO.id()).getPassword())){
                throw new ExistingResourceException("La nueva contrasena es igual a la contraseña antigua");
            } else {
                User user= this.getUserById(userDTO.id());
                user.setPassword(userDTO.newPassword());
                user.setUpdateDate(ZonedDateTime.now());
                userRepository.save(user);
            }
        }
    }
    public void forgotPassword(ForgotPasswordUserDTO oldUser) {
        if (this.getUserById(oldUser.id())==null) {
            throw new ResourceNotFoundException("El usuario que intenta modificar no existe");
        }else {
            if (this.existingPassword(oldUser.id(),oldUser.oldPassword())){
                throw new AuthenticationException("La contrasena actual no coincide");
            } else if (oldUser.oldPassword().equals(this.getUserById(oldUser.id()).getPassword())){
                throw new ExistingResourceException("La nueva contrasena es igual a la contraseña antigua");
            } else {
                User user= this.getUserById(oldUser.id());
                user.setPassword(oldUser.newPassword());
                user.setUpdateDate(ZonedDateTime.now());
                userRepository.save(user);
            }
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Usuario con el ID "+id+", no existe");
        }else {
            userRepository.deleteById(id);
        }
    }
    public Optional<User> loginId(String email, String password){
        if (userRepository.findByEmailAndPassword(email, password)==null){
            throw new ResourceNotFoundException("Credenciales incorrectas");
        }else {
            loginDay(userRepository.findByEmailAndPassword(email, password).getId());
            return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
        }
    }
    private void loginDay(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuraio no encontrado"));
        user.getLoggedDays().add(LocalDateTime.now().getDayOfWeek());
        if (isNewWeek()) {
            user.getLoggedDays().clear();
        }
        userRepository.save(user);
    }

    private boolean isNewWeek() {
        LocalDateTime lastLogin = LocalDateTime.now();
        LocalDateTime startOfLastWeek = lastLogin.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime startOfCurrentWeek = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return startOfLastWeek.isBefore(startOfCurrentWeek);
    }

    private User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    private User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    private Boolean existingPassword(long id, String password){
        return this.getUserById(id).getPassword().equals(password);
    }
}
