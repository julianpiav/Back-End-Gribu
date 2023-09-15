package co.edu.unisabana.UsuariosYContenido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<UsuarioDTO, Integer> {
}
