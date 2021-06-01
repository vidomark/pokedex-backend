package com.codecool.pokedex.model;

public class Ability {

    private String name;
    private boolean isHidden;
    private int slot;
    private String url;

    public Ability(String name, boolean isHidden, int slot, String url) {
        this.name = name;
        this.isHidden = isHidden;
        this.slot = slot;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "name='" + name + '\'' +
                ", isHidden=" + isHidden +
                ", slot=" + slot +
                ", url='" + url + '\'' +
                '}';
    }
}
