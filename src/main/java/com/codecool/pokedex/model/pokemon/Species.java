package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	@Transient
	private String url;
}