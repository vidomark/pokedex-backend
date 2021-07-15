package com.codecool.pokedex.service;

import com.codecool.pokedex.model.user.SecurityUser;
import com.codecool.pokedex.model.user.User;
import com.codecool.pokedex.repository.UserRepository;
import com.codecool.pokedex.model.registration.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public UserService(UserRepository userRepository, ConfirmationTokenService confirmationTokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
        return new SecurityUser(user);
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists)
            throw new IllegalStateException(String.format("Username already exists: %s", user.getUsername()));

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: % not found", username)));
        user.setIsEnabled(true);
    }
}
