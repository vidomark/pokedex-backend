package com.codecool.pokedex;

import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import com.codecool.pokedex.dao.pokemon.util.PokemonContainer;
import com.codecool.pokedex.model.pokemon.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
public class PokedexApplication {

	@Autowired
	private final PokemonRepository pokemonRepository;
	public static final int POKEMON_NUMBER = 200;

	public PokedexApplication(PokemonRepository pokemonRepository) throws IOException {
		this.pokemonRepository = pokemonRepository;
		int currentPokemonNumber = pokemonRepository.findAll().size();

		if (currentPokemonNumber <= 0) { // if database is empty
			PokemonContainer pokemonContainer = new PokemonContainer(POKEMON_NUMBER);
			List<Pokemon> pokemons = pokemonContainer.getPokemons();
			pokemonRepository.saveAll(pokemons);
		}
	}

	public static void main(String[] args) {SpringApplication.run(PokedexApplication.class, args);}
}
