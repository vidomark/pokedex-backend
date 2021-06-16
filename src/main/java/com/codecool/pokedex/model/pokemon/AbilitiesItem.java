package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
public class AbilitiesItem{

	@SerializedName("is_hidden")
	private boolean isHidden;

	@SerializedName("ability")
	private Ability ability;

	@SerializedName("slot")
	private int slot;

	@Embedded
	public Ability getAbility(){
		return ability;
	}
}