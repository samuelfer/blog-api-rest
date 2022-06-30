package br.com.marhasoft.blogapirest.blogapirest.controllers;

import br.com.marhasoft.blogapirest.blogapirest.dtos.LoginDTO;
import br.com.marhasoft.blogapirest.blogapirest.dtos.RegisterDTO;
import br.com.marhasoft.blogapirest.blogapirest.models.Role;
import br.com.marhasoft.blogapirest.blogapirest.models.User;
import br.com.marhasoft.blogapirest.blogapirest.repositories.RoleRepository;
import br.com.marhasoft.blogapirest.blogapirest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/iniciarSession")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Login efetuado com sucesso", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Login já em uso", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Email já em uso", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Optional<Role> role = roleRepository.findByName("ROLE_USER");
        if (role.isPresent()) {
            user.setRoles(Collections.singleton(role.get()));
        }

        userRepository.save(user);
        return new ResponseEntity<>("Usuário registrado com sucesso", HttpStatus.OK);
    }
}
