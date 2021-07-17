package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsItem{

	@SerializedName("stat")
	@Embedded
	private Stat stat;

	@SerializedName("base_stat")
	private Integer baseStat;

	@SerializedName("effort")
	private Integer effort;
}