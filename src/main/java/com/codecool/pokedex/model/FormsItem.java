package com.codecool.pokedex.model;

import com.google.gson.annotations.SerializedName;

public class FormsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getUrl(){
		return url;
	}
}