package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Ability{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getUrl(){
		return url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ability ability = (Ability) o;
		return name.equals(ability.name) && url.equals(ability.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, url);
	}
}