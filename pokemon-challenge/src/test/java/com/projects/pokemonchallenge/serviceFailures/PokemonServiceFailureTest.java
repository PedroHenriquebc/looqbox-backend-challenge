package com.projects.pokemonchallenge.serviceFailures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.projects.pokemonchallenge.model.Pokemon;
import com.projects.pokemonchallenge.service.PokemonService;

@SpringBootTest
public class PokemonServiceFailureTest {

	@Autowired
	PokemonService pokemonService = new PokemonService();
		
	String substring = "xyz";
	ArrayList<Pokemon> pokemonsList = new ArrayList<>();
	Pokemon p1 = new Pokemon("charmeleon", "");
	Pokemon p2 = new Pokemon("charizard", "");
	Pokemon p3 = new Pokemon("charmander", "");
	
	//Teste de url incorreta(Alterar url em PokemonService para uma incorreta para testar)
	@Test
	public void getAllPokemonsExceptionTest(){
		assertThrows(HttpClientErrorException.NotFound.class, () -> pokemonService.getAllPokemons());
	}
	
	@Test
	public void substringFilterFailureTest() {
		ArrayList<Pokemon> listAux = pokemonService.substringFilter(pokemonService.getAllPokemons(), substring);
		for (Pokemon pokemon : listAux) {
			String pokemonName = pokemon.getName();
			assertFalse(pokemonName.contains(substring));
			}
		assertEquals(listAux.size(), 0);
	}
	
	
}
