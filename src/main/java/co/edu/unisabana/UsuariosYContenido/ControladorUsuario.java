package co.edu.unisabana.UsuariosYContenido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/usuarios")
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios() {
        return servicioUsuario.getUsuarios();
    }

    @GetMapping("/{codigo}")
    public Optional<UsuarioDTO> obtenerUsuario(@PathVariable("codigo") int codigo) {
        return servicioUsuario.getUsuario(codigo);
    }

    @PostMapping
    public UsuarioDTO guardaOActualizaUsuario(@RequestBody UsuarioDTO usuario) {
        servicioUsuario.guardaOActualiza(usuario);
        return usuario;
    }

    @DeleteMapping("/{codigo}")
    public void eliminarUsuario(@PathVariable("codigo") int codigo) {
        servicioUsuario.eliminar(codigo);
    }

}
