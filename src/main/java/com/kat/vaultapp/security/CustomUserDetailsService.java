package com.kat.vaultapp.security;

import com.kat.vaultapp.exception.EntityNotFoundException;
import com.kat.vaultapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws EntityNotFoundException {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "User with login " + login + " not found"));
    }
}
