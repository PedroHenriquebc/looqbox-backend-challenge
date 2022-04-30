package com.projects.pokemonchallenge.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.pokemonchallenge.model.Pokemon;
import com.projects.pokemonchallenge.service.PokemonService;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	PokemonService pokemonService = new PokemonService();

	@GetMapping
	public ArrayList<Pokemon> getPokedex(@RequestParam String q) {
		ArrayList<Pokemon> pokemonsList = pokemonService.getAllPokemons();
		return pokemonService.sortedPokedex(pokemonsList, q);
	}
}
