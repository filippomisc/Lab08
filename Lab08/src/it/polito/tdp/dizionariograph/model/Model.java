package it.polito.tdp.dizionariograph.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao = new WordDAO();
	
	Graph<String, DefaultEdge> graph;
	List<String> paroleConNCaratteri;
	

	public void createGraph(int numeroLettere) {
		//leggi la lista degli oggetti dal DB
		this.paroleConNCaratteri = dao.getAllWordsFixedLength(numeroLettere);
		
		//creare il grafo SimpleGraph<>()
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		
		//aggiungere tutti i vertici
		Graphs.addAllVertices(this.graph, this.paroleConNCaratteri);
		System.out.println("vertici aggiunti: " + this.graph.vertexSet().size());
		
		
		//aggiungere gli archi (bisogna creare una lista di parole simili/connesse***)
		
		//ciclo for per ogni parola del database(vertice)
		for(String p : this.paroleConNCaratteri) {
			
			//***
			List<String> paroleConnesse = dao.getAllWordsFixedLengthAndConnected(p, numeroLettere);
			
			//cilco for per le parole destinazione/connesse/simili
			for(String pConn : paroleConnesse) {
				
				if(!p.equals(pConn)){
				this.graph.addEdge(p, pConn);
				}
			}
			}
//			this.addEdges();
		
		System.err.println(String.format("grafo creato: %d vertici, %d grafi\n", graph.vertexSet().size(), graph.edgeSet().size()));
	}

	
	private void addEdges() {
		
		for(String p : this.paroleConNCaratteri) {
			
			List<String> connessi = dao.getAllWordsFixedLengthAndConnected(p, p.length());
			
			for(String pConn : connessi) {

//			ArtObject dest = new ArtObject(aoc.getArtObjectID(), null, null, null, 0, null, null, null, null, null, 0, null, null, null, null, null);

			if(!pConn.equals(p)) {
				this.graph.addEdge(p, pConn);
			}
			
			System.out.format("(%d,%d)\n", p, pConn);
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
