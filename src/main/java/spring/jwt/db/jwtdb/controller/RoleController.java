package spring.jwt.db.jwtdb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.jwt.db.jwtdb.model.Role;
import spring.jwt.db.jwtdb.repository.RoleRepository;

@RestController
@RequestMapping("role")
public class RoleController {
    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("save")
    public Role saveRole(@RequestBody Role role){
        return roleRepository.save(role);
    }
}
