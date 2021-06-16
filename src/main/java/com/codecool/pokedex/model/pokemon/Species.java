package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
public class Species{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	@Transient
	private String url;
}