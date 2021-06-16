package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
@Data
public class Stat{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stat stat = (Stat) o;
		return name.equals(stat.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}