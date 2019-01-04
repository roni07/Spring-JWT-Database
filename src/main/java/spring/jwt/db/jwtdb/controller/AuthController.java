package spring.jwt.db.jwtdb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.jwt.db.jwtdb.model.User;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @PostMapping(value = "sign-up")
    public ResponseEntity<?> signUp() {



        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "")
    public UsernamePasswordAuthenticationToken getToken(@RequestBody User user) {
        return new UsernamePasswordAuthenticationToken(user.getClientName(), user.getUserPassword());

    }
}
