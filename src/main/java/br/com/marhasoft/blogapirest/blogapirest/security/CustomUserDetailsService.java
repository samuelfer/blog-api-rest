package br.com.marhasoft.blogapirest.blogapirest.security;

import br.com.marhasoft.blogapirest.blogapirest.models.Role;
import br.com.marhasoft.blogapirest.blogapirest.models.User;
import br.com.marhasoft.blogapirest.blogapirest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o login "+username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getRoles(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
