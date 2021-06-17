package com.codecool.pokedex.model.pokemon;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull(message = "Pokemon must be defined!")
@NotBlank(message = "Pokemon must be defined!")
public class Pokemon {

    @SerializedName("location_area_encounters")
    private String locationAreaEncounters;

    @SerializedName("types")
    @ElementCollection
    private List<TypesItem> types;

    @SerializedName("base_experience")
    private int baseExperience;

    @SerializedName("weight")
    private int weight;

    @SerializedName("is_default")
    private boolean isDefault;

    @SerializedName("sprites")
    @Embedded
    private Sprites sprites;

    @SerializedName("abilities")
    @ElementCollection
    private List<AbilitiesItem> abilities;

    @SerializedName("species")
    @Embedded
    private Species species;

    @SerializedName("stats")
    @ElementCollection
    private List<StatsItem> stats;

    @SerializedName("name")
    @Column(insertable = false, updatable = false)
    private String name;

    @SerializedName("id")
    @NotBlank
    @NotNull
    @Id
    private int id;

    @SerializedName("forms")
    @ElementCollection
    private List<FormsItem> forms;

    @SerializedName("height")
    private int height;

    @SerializedName("order")
    @Transient
    private int order;
}