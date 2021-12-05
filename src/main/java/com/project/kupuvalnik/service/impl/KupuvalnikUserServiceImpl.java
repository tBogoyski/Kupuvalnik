package com.project.kupuvalnik.service.impl;

import com.project.kupuvalnik.models.entity.UserEntity;
import com.project.kupuvalnik.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KupuvalnikUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public KupuvalnikUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // The purpose of this method is to map our user representation (UserEntity)
    // to the user representation in the spring security world (UserDetails).
    // The only thing that spring will provide to us is the username.
    // The username will come from the HTML login form.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username" + username + " not found!"));

        return mapToDetails(user);
    }

    private UserDetails mapToDetails(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());

        return new KupuvalnikUser(user.getUsername(), user.getPassword(), authorities);
    }
}
