package com.codecool.pokedex.model.user;

import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.registration.ConfirmationToken;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String username;

    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Pokemon> pokemons = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ConfirmationToken> confirmationTokens;
}
