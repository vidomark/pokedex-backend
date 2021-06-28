package com.codecool.pokedex;

import com.codecool.pokedex.config.PokemonLoaderConfig;
import com.codecool.pokedex.repository.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.util.PokemonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties(value = PokemonLoaderConfig.class)
public class PokedexApplication {

	private final PokemonRepository pokemonRepository;
	private final PokemonLoaderConfig pokemonLoaderConfig;

	@Autowired
	public PokedexApplication(PokemonRepository pokemonRepository, PokemonLoaderConfig pokemonLoaderConfig) throws IOException {
		this.pokemonRepository = pokemonRepository;
		this.pokemonLoaderConfig = pokemonLoaderConfig;

		Integer currentPokemonNumber = pokemonRepository.findAll().size();
		Integer numberOfPokemons = pokemonLoaderConfig.getNumber();
		Boolean loadPokemons = pokemonLoaderConfig.getLoad(); // on test it's false

		if (currentPokemonNumber <= 0 && loadPokemons) { // if database is empty
			PokemonContainer pokemonContainer = new PokemonContainer(numberOfPokemons);
			List<Pokemon> pokemons = pokemonContainer.getPokemons();
			pokemonRepository.saveAll(pokemons);
		}
	}

	public static void main(String[] args) {SpringApplication.run(PokedexApplication.class, args);}
}
