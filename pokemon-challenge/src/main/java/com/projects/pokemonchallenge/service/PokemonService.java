package com.projects.pokemonchallenge.service;

import com.projects.pokemonchallenge.model.Pokedex;
import com.projects.pokemonchallenge.model.Pokemon;
import com.projects.pokemonchallenge.util.Ordenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class PokemonService {

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private Ordenation ordenation;

	private String url = "https://pokeapi.co/api/v2/pokemon?limit=10000";

	//CONSOME API E RETORNA OS POKEMONS LIDOS NUMA LISTA
	public ArrayList<Pokemon> getAllPokemons() {
		Pokedex pokedex = template.getForObject(url, Pokedex.class);
		ArrayList<Pokemon> pokemonsList = pokedex.getResults();
		return pokemonsList;
	}

	//FILTRA E RETORNA APENAS POKEMONS QUE CONTEM UMA SUBSTRING NO SEU NOME
	public ArrayList<Pokemon> substringFilter(ArrayList<Pokemon> pokemonsList, String substring) {
		ArrayList<Pokemon> filteredList = new ArrayList<>();
		for (Pokemon pokemon : pokemonsList) {
			String pokemonName = pokemon.getName();
			if (pokemonName.contains(substring)) {
				filteredList.add(pokemon);
			}
		}
		return filteredList;
	}
	
	public ArrayList<Pokemon> sortByAlphabet(ArrayList<Pokemon> pokemonsList) {
		return ordenation.quickSortAlphabetical(pokemonsList,  0, pokemonsList.size() - 1);
	}
	
	public ArrayList<Pokemon> sortByLength(ArrayList<Pokemon> pokemonsList){
		return ordenation.quickSortLength(pokemonsList, 0, pokemonsList.size() - 1);
	}
	

	//INSERE MARCAÇÃO NA SUBSTRING EM UM NOME DE POKEMON
	public ArrayList<Pokemon> highlightSubstring(ArrayList<Pokemon> pokemonsList, String substring) {
		String preStart = "<pre>";
		String preEnd = "</pre>";

		//Percorre a lista de pokemons inserindo preStart e preEnd na substring e guardando o resultado como atributo
		for (Pokemon pokemon : pokemonsList) {
			String pokemonName = pokemon.getName();
			String highlightName = pokemonName.replace(substring, preStart + substring + preEnd);
			pokemon.setHighlight(highlightName);
		}

		return pokemonsList;
	}
	
	//RETORNA LISTA COM POKEMONS FILTRADOS, ORDENADOS E COM MARCAÇÃO
	public ArrayList<Pokemon> organizePokemons(ArrayList<Pokemon> pokemonsList, String substring){
		ArrayList<Pokemon> filteredList = substringFilter(pokemonsList, substring);
		ArrayList<Pokemon> alphabeticalOrdened = sortByAlphabet(filteredList);
		ArrayList<Pokemon> lengthOrdened = sortByLength(alphabeticalOrdened);
		ArrayList<Pokemon> sortedList = highlightSubstring(lengthOrdened, substring);
		return sortedList;
		
	}
}
