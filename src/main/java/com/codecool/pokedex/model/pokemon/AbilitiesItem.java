package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;

public class AbilitiesItem{

	@SerializedName("is_hidden")
	private boolean isHidden;

	@SerializedName("ability")
	private Ability ability;

	@SerializedName("slot")
	private int slot;

	public boolean isIsHidden(){
		return isHidden;
	}

	public Ability getAbility(){
		return ability;
	}

	public int getSlot(){
		return slot;
	}
}