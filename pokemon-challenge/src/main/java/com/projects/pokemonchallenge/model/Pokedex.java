package com.projects.pokemonchallenge.model;

import java.util.ArrayList;

public class Pokedex {

	private ArrayList<Pokemon> results = new ArrayList<>();

	public Pokedex(ArrayList<Pokemon> results) {
		this.results = results;
	}

	public Pokedex() {

	}

	public ArrayList<Pokemon> getResults() {
		return results;
	}

	public void setResults(ArrayList<Pokemon> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Pokedex [results=" + results + "]";
	}

}
