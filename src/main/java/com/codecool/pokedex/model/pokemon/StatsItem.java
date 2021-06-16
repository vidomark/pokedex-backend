package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
public class StatsItem{

	@SerializedName("stat")
	@Embedded
	private Stat stat;

	@SerializedName("base_stat")
	private int baseStat;

	@SerializedName("effort")
	private int effort;

	public int getBaseStat(){
		return baseStat;
	}

	public int getEffort(){
		return effort;
	}


}