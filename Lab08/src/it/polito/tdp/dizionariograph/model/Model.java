package it.polito.tdp.dizionariograph.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao = new WordDAO();
	
	Graph<String, DefaultWeightedEdge> graph;
	List<String> paroleConNCaratteri;
	

	public void createGraph(int numeroLettere) {
		//leggi la lista degli oggetti dal DB
		this.paroleConNCaratteri = dao.getAllWordsFixedLength(numeroLettere);
		
		//creare il grafo SimpleWeightedGraph<>()
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		//aggiungere tutti i vertici
		Graphs.addAllVertices(this.graph, this.paroleConNCaratteri);
		
		//aggiungere gli archi pesati (bisogna creare una lista di archi
		addEdges();

		System.err.println("createGraph -- TODO");
	}

	
	private void addEdges() {
		
		for(String p : this.paroleConNCaratteri) {
			
			List<String> connessi = dao.getAllWordsFixedLengthAndConnected(p, p.length());
			
			for(ArtObjectsAndCount aoc : connessi) {

			ArtObject dest = new ArtObject(aoc.getArtObjectID(), null, null, null, 0, null, null, null, null, null, 0, null, null, null, null, null);

			Graphs.addEdge(this.graph, ao, dest, aoc.getCount());
//			System.out.format("(%d,%d) peso %d\n", ao.getId(), dest.getId(), aoc.getCount());
			}
		}		
	}

	public List<String> displayNeighbours(String parolaInserita) {

		System.err.println("displayNeighbours -- TODO");//TODO
		return new ArrayList<String>();
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");//TODO
		return -1;
	}

//	public boolean existLenght(int lunghezzaParola) {
//		
//		return false;
//	}

	public boolean existWord(String parola) {
		List<String> WordsPrefix = this.getListOfWordsWithLenght(parola.length());
		if(!WordsPrefix.contains(parola))
			return false;
		return true;
	}
	
//	public String cercaParolaPrefix(List<String> parolePrefix) {
//		
//		
//		parolePrefix = this.getListOfWordsWithLenght()
//		String result = "";
//		
//		for(String p : parolePrefix) {
//			if(parolePrefix.contains(o))
//			
//		}
//		return result;
//	}

	public List<String> getListOfWordsWithLenght(int numLettere) {
		return dao.getAllWordsFixedLength(numLettere); 
	}
}
