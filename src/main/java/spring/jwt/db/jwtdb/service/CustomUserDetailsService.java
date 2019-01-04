package spring.jwt.db.jwtdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.jwt.db.jwtdb.model.User;
import spring.jwt.db.jwtdb.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {

        Optional<User> user = userRepository.findByClientName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));


        return user.get();
    }
}
