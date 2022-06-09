package co.com.sofka.questions.security.repositories;


import co.com.sofka.questions.security.collections.Rol;
import co.com.sofka.questions.security.enums.RolName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends MongoRepository<Rol, String> {
    Optional<Rol> findByRolName(RolName rolname);
}
