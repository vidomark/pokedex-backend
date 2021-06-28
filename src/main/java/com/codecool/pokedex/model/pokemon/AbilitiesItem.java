package com.codecool.pokedex.model.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbilitiesItem{

	@SerializedName("is_hidden")
	private Boolean isHidden;

	@SerializedName("ability")
	private Ability ability;

	@SerializedName("slot")
	private Integer slot;

	@Embedded
	public Ability getAbility(){
		return ability;
	}
}