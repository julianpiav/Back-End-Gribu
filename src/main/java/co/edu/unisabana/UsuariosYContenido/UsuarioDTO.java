package co.edu.unisabana.UsuariosYContenido;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_usuario")
public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    private String usuario;
    private String contrasena;
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;
    private float progreso;
    private int nivel;

}
