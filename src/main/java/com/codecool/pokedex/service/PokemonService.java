package com.codecool.pokedex.service;

import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Set<Pokemon> getPokemons() {
        return pokemonRepository.getPokemons();
    }

    public boolean addPokemon(Pokemon pokemon) {
        return pokemonRepository.addPokemon(pokemon);
    }

    public Pokemon getPokemon(int id) {
        return pokemonRepository.getPokemon(id);
    }

    public Set<Pokemon> getPokemonsByType(Type type) {
        return pokemonRepository.getPokemonsByType(type);
    }

    public Set<Type> getTypes() {
        return pokemonRepository.getTypes();
    }

    public Set<Ability> getAbilities() {
        return pokemonRepository.getAbilities();
    }

    public Set<Pokemon> getPokemonsByAbility(Ability ability) {
        return pokemonRepository.getPokemonsByAbility(ability);
    }
}
