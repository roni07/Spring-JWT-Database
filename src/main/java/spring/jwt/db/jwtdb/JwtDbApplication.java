package spring.jwt.db.jwtdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.jwt.db.jwtdb.model.Role;
import spring.jwt.db.jwtdb.model.User;
import spring.jwt.db.jwtdb.repository.RoleRepository;
import spring.jwt.db.jwtdb.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JwtDbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JwtDbApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Iterable<User> allUser = userRepository.findAll();
        if (allUser == null){
            List<Role> roleList = new ArrayList<>();
            Role admin = roleRepository.save(new Role(1, "ADMIN"));
            roleList.add(admin);
            userRepository.save(new User("roni",
                    new BCryptPasswordEncoder().encode("r"),
                    roleList));
        }

    }
}

