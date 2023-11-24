package co.edu.unisabana.Gribu.dto;

public record ForgotPasswordUserDTO(
            Long id,
            String newPassword,
            String recoveryToken
    ){
}
