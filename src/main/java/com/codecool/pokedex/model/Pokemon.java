package com.codecool.pokedex.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

	@SerializedName("location_area_encounters")
	private String locationAreaEncounters;

	@SerializedName("types")
	private List<TypesItem> types;

	@SerializedName("base_experience")
	private int baseExperience;

	@SerializedName("weight")
	private int weight;

	@SerializedName("is_default")
	private boolean isDefault;

	@SerializedName("sprites")
	private Sprites sprites;

	@SerializedName("abilities")
	private List<AbilitiesItem> abilities;

	@SerializedName("species")
	private Species species;

	@SerializedName("stats")
	private List<StatsItem> stats;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("forms")
	private List<FormsItem> forms;

	@SerializedName("height")
	private int height;

	@SerializedName("order")
	private int order;

	public String getLocationAreaEncounters(){
		return locationAreaEncounters;
	}

	public List<TypesItem> getTypes(){
		return types;
	}

	public int getBaseExperience(){
		return baseExperience;
	}

	public int getWeight(){
		return weight;
	}

	public boolean isIsDefault(){
		return isDefault;
	}

	public Sprites getSprites(){
		return sprites;
	}

	public List<AbilitiesItem> getAbilities(){
		return abilities;
	}

	public Species getSpecies(){
		return species;
	}

	public List<StatsItem> getStats(){
		return stats;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public List<FormsItem> getForms(){
		return forms;
	}

	public int getHeight(){
		return height;
	}

	public int getOrder(){
		return order;
	}

	@Override
	public String toString() {
		return "Pokemon{" +
				"locationAreaEncounters='" + locationAreaEncounters + '\'' +
				", types=" + types +
				", baseExperience=" + baseExperience +
				", weight=" + weight +
				", isDefault=" + isDefault +
				", sprites=" + sprites +
				", abilities=" + abilities +
				", species=" + species +
				", stats=" + stats +
				", name='" + name + '\'' +
				", id=" + id +
				", forms=" + forms +
				", height=" + height +
				", order=" + order +
				'}';
	}
}