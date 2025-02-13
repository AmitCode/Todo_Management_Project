package com.todo.management.security;

import com.todo.management.pojo.Users;
import com.todo.management.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailSecurityService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(
                ()-> new UsernameNotFoundException("User Details not exists by UserName or Email")
        );
        Set<GrantedAuthority> authoritySet = user.getRoles().stream().map(
                (role -> new SimpleGrantedAuthority(role.getRoleName()))
        ).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,null,authoritySet);
    }
}
