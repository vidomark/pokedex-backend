package com.codecool.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PokedexApplication {
	/* If initiated for the first time, uncomment section below to fetch data and save to database!
		Set ddl in application.properties to create

		Comment section is database is populated!
		Set ddl in application.properties to none*/


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
