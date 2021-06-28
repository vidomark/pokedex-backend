package com.codecool.pokedex.repository;

import com.codecool.pokedex.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<UserDetails> findByUsername(String username);

    @Query("UPDATE User user " +
                "SET user.enabled = true  " +
                "WHERE user.id = :id")
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void enableUser(int id);
}
