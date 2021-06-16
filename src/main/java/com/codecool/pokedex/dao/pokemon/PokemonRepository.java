package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query("SELECT DISTINCT typeItem.type FROM Pokemon pokemon " +
                "INNER JOIN pokemon.types typeItem")
    List<Type> getTypes();

    @Query("SELECT DISTINCT abilityItem.ability FROM Pokemon pokemon " +
                "INNER JOIN pokemon.abilities abilityItem")
    List<Ability> getAbilities();

    @Query("SELECT pokemon FROM Pokemon pokemon " +
                "INNER JOIN pokemon.types typeItem " +
                "WHERE typeItem.type = :type")
    List<Pokemon> getPokemonsByType(Type type);

    @Query("SELECT pokemon FROM Pokemon pokemon " +
                "INNER JOIN pokemon.abilities abilityItem " +
                "WHERE abilityItem.ability = :ability")
    List<Pokemon> getPokemonsByAbility(Ability ability);
}
