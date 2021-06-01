package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Type{

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
		Type type = (Type) o;
		return name.equals(type.name) && url.equals(type.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, url);
	}
}