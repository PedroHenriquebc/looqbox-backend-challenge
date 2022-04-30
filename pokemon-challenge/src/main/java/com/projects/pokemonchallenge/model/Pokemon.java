package com.projects.pokemonchallenge.model;

public class Pokemon {

	private String name;
	private String highlight;

	public Pokemon(String name, String highlight) {
		this.name = name;
		this.highlight = highlight;
	}

	public Pokemon() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	@Override
	public String toString() {
		return "Pokemon [name=" + name + ", highlight=" + highlight + "]";
	}

}
