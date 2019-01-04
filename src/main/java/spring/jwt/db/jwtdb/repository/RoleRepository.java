package spring.jwt.db.jwtdb.repository;

import org.springframework.data.repository.CrudRepository;
import spring.jwt.db.jwtdb.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
