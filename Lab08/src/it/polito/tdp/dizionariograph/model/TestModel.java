package it.polito.tdp.dizionariograph.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		//test sull'input
		
		model.getListOfWordsWithLenght(4);
		
		
		
		
		
		
		
		
		//test sul grafo 
		model.createGraph(4);
		System.out.println(String.format("**Grafo creato**\n"));
		
		List<String> vicini = model.displayNeighbours("casa");
		System.out.println("Neighbours di casa: " + vicini + "\n");
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree());
	}

}
