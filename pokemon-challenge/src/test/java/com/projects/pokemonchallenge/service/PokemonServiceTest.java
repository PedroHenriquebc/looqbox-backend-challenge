package com.projects.pokemonchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projects.pokemonchallenge.model.Pokemon;


@SpringBootTest
public class PokemonServiceTest {
	
	@Autowired
	PokemonService pokemonService = new PokemonService();
	
	String substring = "char";
	ArrayList<Pokemon> pokemonsList = new ArrayList<>();
	Pokemon p1 = new Pokemon("charmeleon", "");
	Pokemon p2 = new Pokemon("charizard", "");
	Pokemon p3 = new Pokemon("charmander", "");
	
	
	
	@Test
	public void getAllPokemonsTest() {
		assertNotNull(pokemonService.getAllPokemons());
		assertEquals(pokemonService.getAllPokemons().size(), 1126);
		assertEquals(pokemonService.getAllPokemons().get(0).getName(), "bulbasaur");
		assertEquals(pokemonService.getAllPokemons().get(19).getName(), "raticate");
	}
	
	@Test
	public void substringFilterTest() {
		ArrayList<Pokemon> listAux = pokemonService.substringFilter(pokemonService.getAllPokemons(), substring);
		for (Pokemon pokemon : listAux) {
			String pokemonName = pokemon.getName();
			assertTrue(pokemonName.contains(substring));
			}
		assertNotEquals(listAux.size(), 0);
	}
		
	@Test
	public void sortByAlphabetTest() {
		pokemonsList.add(p1);
		pokemonsList.add(p2);
		pokemonsList.add(p3);
		ArrayList<Pokemon> listAux = pokemonService.sortByAlphabet(pokemonsList);
		assertEquals(listAux.get(0).getName(), "charizard");
		assertEquals(listAux.get(1).getName(), "charmander");
		assertEquals(listAux.get(2).getName(), "charmeleon");
	}

	@Test
	public void sortByLengthTest(){
		pokemonsList.add(p1);
		pokemonsList.add(p2);
		pokemonsList.add(p3);
		ArrayList<Pokemon> listAux = pokemonService.sortByLength(pokemonsList);
		assertEquals(listAux.get(0).getName(), "charizard");
		assertEquals(listAux.get(1).getName(), "charmeleon");
		assertEquals(listAux.get(2).getName(), "charmander");
	}
	
	@Test
	public void highlightSubstringTest() {
		pokemonsList.add(p1);
		pokemonsList.add(p2);
		pokemonsList.add(p3);
		ArrayList<Pokemon> listAux = pokemonService.highlightSubstring(pokemonsList, substring);
		for (Pokemon pokemon : listAux) {
			String highlightName = pokemon.getHighlight();
			assertTrue(highlightName.contains("<pre>" + substring + "</pre>"));
			}
		assertNotEquals(listAux.size(), 0);
	}
	

	public void organizePokemonsTest(){
		pokemonsList.add(p1);
		pokemonsList.add(p2);
		pokemonsList.add(p3);
		ArrayList<Pokemon> listAux = pokemonService.organizePokemons(pokemonsList, substring);
		assertEquals(listAux.get(0).getName(), "charizard");
		assertEquals(listAux.get(1).getName(), "charmander");
		assertEquals(listAux.get(2).getName(), "charmeleon");
	}
	
}
	


