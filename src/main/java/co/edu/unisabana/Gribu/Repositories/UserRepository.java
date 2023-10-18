package co.edu.unisabana.Gribu.Repositories;

import co.edu.unisabana.Gribu.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsernameAndPassword(String username, String password);

    public User findByEmail(String email);

    public User findByUsername(String username);
}
