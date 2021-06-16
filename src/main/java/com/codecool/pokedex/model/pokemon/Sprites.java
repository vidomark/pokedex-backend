package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
public class Sprites{

	@SerializedName("back_default")
	private String backDefault;

	@SerializedName("front_default")
	private String frontDefault;

	@SerializedName("back_shiny")
	@Transient
	private String backShiny;

	@SerializedName("front_shiny")
	@Transient
	private String frontShiny;
}