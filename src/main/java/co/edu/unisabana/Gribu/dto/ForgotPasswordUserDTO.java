package co.edu.unisabana.Gribu.dto;

public record ForgotPasswordUserDTO(
            Long id,
            String oldPassword,
            String newPassword
    ){
}
