package com.projects.pokemonchallenge.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.projects.pokemonchallenge.model.Pokedex;
import com.projects.pokemonchallenge.model.Pokemon;

@Service
public class PokemonService {

	@Autowired
	RestTemplate template = new RestTemplate();

	private String url = "https://pokeapi.co/api/v2/pokemon/";

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

	//ORDENA LISTA DE POKEMONS POR ORDEM ALFABETICA UTILIZANDO BUBBLESORT
	public ArrayList<Pokemon> alphabeticalOrdering(ArrayList<Pokemon> pokemonsList) {
		for (int i = 0; i < pokemonsList.size() - 1; i++) {
			boolean estaOrdenado = true;
			for (int j = 0; j < pokemonsList.size() - 1 - i; j++) {
				//Compara se o nome na posição j é maior alfabeticamente que o nome na posição j + 1
				if (pokemonsList.get(j).getName().compareTo(pokemonsList.get(j + 1).getName()) > 0) {
					//Troca o nome da posição j para j + 1 e vice-versa
					Pokemon aux = pokemonsList.get(j);
					pokemonsList.set(j, pokemonsList.get(j + 1));
					pokemonsList.set(j + 1, aux);
					estaOrdenado = false;
				}
			}
			 if(estaOrdenado)
			        break;
		}
		return pokemonsList;
	}
	
	//ORDENA LISTA DE POKEMONS POR TAMANHO UTILIZANDO BUBBLESORT
	public ArrayList<Pokemon> lengthOrdering(ArrayList<Pokemon> pokemonsList){
		for (int i = 0; i < pokemonsList.size() - 1; i++) {
			boolean estaOrdenado = true;
			//Compara se o tamanho do nome na posição j é maior que o do nome na posição j + 1
			for (int j = 0; j < pokemonsList.size() - 1 - i; j++) {
				if (pokemonsList.get(j).getName().length() > pokemonsList.get(j + 1).getName().length()) {
					//Troca o nome da posição j para j + 1 e vice-versa
					Pokemon aux = pokemonsList.get(j);
					pokemonsList.set(j, pokemonsList.get(j + 1));
					pokemonsList.set(j + 1, aux);
					estaOrdenado = false;
				}
			}
			 if(estaOrdenado)
			        break;
		}
		return pokemonsList;
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
	public ArrayList<Pokemon> sortedPokedex(ArrayList<Pokemon> pokemonsList, String substring){
		ArrayList<Pokemon> filteredList = substringFilter(pokemonsList, substring);
		ArrayList<Pokemon> alphabeticalOrdened = alphabeticalOrdering(filteredList);
		ArrayList<Pokemon> lengthOrdened = lengthOrdering(alphabeticalOrdened);
		ArrayList<Pokemon> sortedList = highlightSubstring(lengthOrdened, substring);
		return sortedList;
		
	}
}
