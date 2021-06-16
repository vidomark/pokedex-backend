package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class FormsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;
}