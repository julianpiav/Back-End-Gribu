package co.edu.unisabana.Gribu.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {

  private String user;
  private String clave;

}
