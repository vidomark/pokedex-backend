package com.codecool.pokedex.repository;

import com.codecool.pokedex.service.registration.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

    @Query("SELECT confirmationToken " +
                "FROM ConfirmationToken confirmationToken " +
                "WHERE confirmationToken.token = :token")
    Optional<ConfirmationToken> getToken(String token);

    @Query("UPDATE ConfirmationToken confirmationToken " +
                "SET confirmationToken.confirmedAt = :now " +
                "WHERE confirmationToken.token = :token")
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    int updateConfirmedAt(String token, LocalDateTime now);
}
