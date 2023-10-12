package br.com.rsoft.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsernameAndAndPassword(String username, String password);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
