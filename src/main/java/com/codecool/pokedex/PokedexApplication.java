package com.codecool.pokedex;

import com.codecool.pokedex.config.PokemonLoaderConfig;
import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import com.codecool.pokedex.dao.pokemon.util.PokemonContainer;
import com.codecool.pokedex.model.pokemon.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties(value = PokemonLoaderConfig.class)
public class PokedexApplication {

	@Autowired
	private final PokemonRepository pokemonRepository;

	@Autowired
	private final PokemonLoaderConfig pokemonLoaderConfig;

	public static final int POKEMON_NUMBER = 200;

	public PokedexApplication(PokemonRepository pokemonRepository, PokemonLoaderConfig pokemonLoaderConfig) throws IOException {
		this.pokemonRepository = pokemonRepository;
		this.pokemonLoaderConfig = pokemonLoaderConfig;
		int currentPokemonNumber = pokemonRepository.findAll().size();
		boolean loadPokemons = pokemonLoaderConfig.getLoad(); // on test it's false

		if (currentPokemonNumber <= 0 && loadPokemons) { // if database is empty
			PokemonContainer pokemonContainer = new PokemonContainer(POKEMON_NUMBER);
			List<Pokemon> pokemons = pokemonContainer.getPokemons();
			pokemonRepository.saveAll(pokemons);
		}
	}

	public static void main(String[] args) {SpringApplication.run(PokedexApplication.class, args);}
}
