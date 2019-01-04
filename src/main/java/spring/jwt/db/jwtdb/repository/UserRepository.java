package spring.jwt.db.jwtdb.repository;

import org.springframework.data.repository.CrudRepository;
import spring.jwt.db.jwtdb.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByClientName(String username);

}
