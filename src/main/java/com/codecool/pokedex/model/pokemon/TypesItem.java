package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;

public class TypesItem{

	@SerializedName("slot")
	private int slot;

	@SerializedName("type")
	private Type type;

	public int getSlot(){
		return slot;
	}

	public Type getType(){
		return type;
	}
}