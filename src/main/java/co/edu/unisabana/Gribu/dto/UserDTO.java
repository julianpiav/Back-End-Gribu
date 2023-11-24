package co.edu.unisabana.Gribu.dto;


import co.edu.unisabana.Gribu.entity.UserAvatar;

import java.time.DayOfWeek;
import java.util.Set;

public record UserDTO (
        Long id,
        String email,
        String username,
        String name,
        Integer level,
        Set<DayOfWeek> loggedDays,
        UserAvatar userAvatar,
        Float watchedMinutes,
        Integer watchedLessons
){

}
