package com.codecool.pokedex.service;

import com.codecool.pokedex.repository.pokemon.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> getPokemon(int id) {
        return pokemonRepository.findById(id);
    }

    public List<Pokemon> getPokemonsByType(Type type) {
        return pokemonRepository.getPokemonsByType(type);
    }

    public List<Type> getTypes() {
        return pokemonRepository.getTypes();
    }

    public List<Ability> getAbilities() {
        return pokemonRepository.getAbilities();
    }

    public List<Pokemon> getPokemonsByAbility(Ability ability) {
        return pokemonRepository.getPokemonsByAbility(ability);
    }
}
