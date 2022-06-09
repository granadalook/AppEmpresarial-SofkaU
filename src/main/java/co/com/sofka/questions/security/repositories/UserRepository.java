package co.com.sofka.questions.security.repositories;

import co.com.sofka.questions.security.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByRol(String rol);
    boolean existsByUsername(String username);

    boolean existsByEmail(String Email);

}
