package co.edu.unisabana.UsuariosYContenido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {
    @Autowired
    RepositorioUsuario repositorioUsuario;

    public List<UsuarioDTO> getUsuarios() {
        return repositorioUsuario.findAll();
    }

    public Optional<UsuarioDTO> getUsuario(int codigo) {
        return repositorioUsuario.findById(codigo);
    }

    public void guardaOActualiza(UsuarioDTO usuario) {
        repositorioUsuario.save(usuario);
    }

    public void eliminar(int codigo) {
        repositorioUsuario.deleteById(codigo);
    }

}
