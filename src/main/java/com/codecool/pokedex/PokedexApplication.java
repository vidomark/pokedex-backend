package com.codecool.pokedex;

import com.codecool.pokedex.dao.pokemon.jpa.pokemonRepository;
import com.codecool.pokedex.dao.pokemon.util.PokemonContainer;
import com.codecool.pokedex.model.pokemon.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class PokedexApplication {
	/* If initiated for the first time, uncomment section below to fetch data and save to database!
		Comment section is database is populated!*/


	/*@Autowired
	private final pokemonRepository pokemonRepository;
	private final PokemonContainer pokemonContainer = new PokemonContainer();

	public PokedexApplication(pokemonRepository pokemonRepository) throws IOException {
		this.pokemonRepository = pokemonRepository;
		List<Pokemon> pokemons = pokemonContainer.getPokemons();
		pokemonRepository.saveAll(pokemons);
	}*/

	public static void main(String[] args) {SpringApplication.run(PokedexApplication.class, args);}
}
