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
public class Pokemon {

    @SerializedName("location_area_encounters")
    private String locationAreaEncounters;

    @SerializedName("types")
    @ElementCollection
    private List<TypesItem> types;

    @SerializedName("base_experience")
    private Integer baseExperience;

    @SerializedName("weight")
    private Integer weight;

    @SerializedName("is_default")
    private Boolean isDefault;

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
    @Id
    private Integer id;

    @SerializedName("forms")
    @ElementCollection
    private List<FormsItem> forms;

    @SerializedName("height")
    private Integer height;

    @SerializedName("order")
    @Transient
    private Integer order;
}