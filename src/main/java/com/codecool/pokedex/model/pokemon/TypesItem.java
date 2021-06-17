package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypesItem{

	@SerializedName("slot")
	private int slot;

	@SerializedName("type")
	@Embedded
	private Type type;
}