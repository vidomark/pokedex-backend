package com.codecool.pokedex.service.registration;

import com.codecool.pokedex.model.user.User;
import com.codecool.pokedex.service.ConfirmationTokenService;
import com.codecool.pokedex.service.UserService;
import com.codecool.pokedex.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.codecool.pokedex.model.user.UserRole.USER;

@Service
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;

    @Autowired
    public RegistrationService(UserService userService, ConfirmationTokenService confirmationTokenService, EmailValidator emailValidator) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailValidator = emailValidator;
    }

    public String register(RegistrationRequest request) {
        boolean validEmail = emailValidator.test(request.getEmail());

        if (!validEmail)
            throw new IllegalStateException(String.format("Invalid email: %s", request.getEmail()));

        return userService.signUpUser(
                User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .enabled(false)
                    .locked(false)
                    .role(USER)
                    .build()
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException(String.format("Token not found: %s", token)));

        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Email already confirmed");

        LocalDateTime expiredAt = confirmationToken.getExpriesAt();
        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token expired");

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getId());
        return "confirmed";
    }
}
