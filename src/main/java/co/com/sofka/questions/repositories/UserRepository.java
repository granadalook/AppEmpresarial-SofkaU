package co.com.sofka.questions.repositories;


import co.com.sofka.questions.collections.UserInto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends ReactiveMongoRepository<UserInto, String> {

    /*Mono<UserInto> findByUsername(String username);
    boolean existsByUsername(String username);
    Mono<UserInto> findUserByUsername(String username);*/

}