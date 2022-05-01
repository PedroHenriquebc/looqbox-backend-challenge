package com.projects.pokemonchallenge.util;

import java.util.ArrayList;
import java.util.Collections;

import com.projects.pokemonchallenge.model.Pokemon;

public class Ordenation {
	/*
	-	Algoritmos baseados em Quick Sort.
	-	Os métodos "partition..." Escolhe um pivot e o coloca em uma posição tal que todos os elementos à esquerda 
	 	são menores ou iguais e todos os elementos à direita são maiores.
	-	Os métodos "quickSort..." Leva em consideração a esquerda e a direta do pivot execuçutando consectivos particionamentos. 
	*/
	
	public int partitionAlphabetical(ArrayList<Pokemon> pokemonsList, int left, int right) {
		Pokemon pivot = pokemonsList.get(left);
		int  i = left;
		
		for(int j = left + 1; j <= right; j++) {
			if(pokemonsList.get(j).getName().compareTo(pivot.getName()) < 0) {
				i += 1;
				Collections.swap(pokemonsList, i, j);
			}
		}
		
		Collections.swap(pokemonsList, left, i);
		return i;
	}
	
	public int partitionLength(ArrayList<Pokemon> pokemonsList, int left, int right) {
		Pokemon pivot = pokemonsList.get(left);
		int  i = left;
		
		for(int j = left + 1; j <= right; j++) {
			if(pokemonsList.get(j).getName().length() < pivot.getName().length()) {
				i += 1;
				Collections.swap(pokemonsList, i, j);
			}
		}
		
		Collections.swap(pokemonsList, left, i);
		return i;
	}
	
	public ArrayList<Pokemon> quickSortAlphabetical(ArrayList<Pokemon> pokemonsList, int left, int right) {
		if(left < right) {
			int indexPivot = partitionAlphabetical(pokemonsList, left, right);
			quickSortAlphabetical(pokemonsList, left, indexPivot - 1);
			quickSortAlphabetical(pokemonsList, indexPivot + 1, right);
		}
		return pokemonsList;
	}
	
	public ArrayList<Pokemon> quickSortLength(ArrayList<Pokemon> pokemonsList, int left, int right) {
		if(left < right) {
			int indexPivot = partitionLength(pokemonsList, left, right);
			quickSortLength(pokemonsList, left, indexPivot - 1);
			quickSortLength(pokemonsList, indexPivot + 1, right);
		}
		return pokemonsList;
	}
}