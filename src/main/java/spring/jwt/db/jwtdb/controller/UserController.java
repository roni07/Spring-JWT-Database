package spring.jwt.db.jwtdb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.jwt.db.jwtdb.model.Role;
import spring.jwt.db.jwtdb.model.User;
import spring.jwt.db.jwtdb.repository.RoleRepository;
import spring.jwt.db.jwtdb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest")
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("save/{role}")
    public User saveUser(@RequestBody User user, @PathVariable String role){

        List<Role> roleList = new ArrayList<>();
        Role createdRole = roleRepository.findByRole(role);
        roleList.add(createdRole);
        user.setRoles(roleList);
        String pass = new BCryptPasswordEncoder().encode(user.getUserPassword());
        user.setUserPassword(pass);
        return userRepository.save(user);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("user")
    public String getUser(){
        return "User is success";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("admin")
    public String getAdmin(){
        return "Admin is success";
    }

    @GetMapping("all")
    public Iterable<User> getAll(){
        return userRepository.findAll();
    }

}
