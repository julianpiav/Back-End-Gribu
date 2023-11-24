package co.edu.unisabana.Gribu.dto;

import co.edu.unisabana.Gribu.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getName(),
                user.getLevel(),
                user.getLoggedDays(),
                user.getAvatar(),
                user.getWatchedMinutes(),
                user.getWatchedLessons()
        );
    }
}
