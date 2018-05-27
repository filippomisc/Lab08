package it.polito.tdp.dizionariograph.model;

import java.util.*;


import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private WordDAO dao;
	
	private Graph<String, DefaultEdge> graph;
	private List<String> paroleConNCaratteri;
	private int numLettere;
	
	

	public Model() {
		dao = new WordDAO();		
		numLettere = 0;
	}

	

	public int getNumLettere() {
		return numLettere;
	}

	public void createGraph(int numeroLettere) {
		
		this.numLettere=numeroLettere;
		
		//leggi la lista degli oggetti dal DB
		this.paroleConNCaratteri = dao.getAllWordsFixedLength(numeroLettere);
		
		//creare il grafo SimpleGraph<>()
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		
		
		//aggiungere tutti i vertici
		Graphs.addAllVertices(this.graph, this.paroleConNCaratteri);
//		System.out.println("vertici aggiunti: " + this.graph.vertexSet().size());
		
		
		//aggiungere gli archi (bisogna creare una lista di parole simili/connesse***)
		
		//ciclo for per ogni parola del database(vertice)
		for(String p : this.paroleConNCaratteri) {
			
			//***
//			Utilities u = new Utilities();
			List<String> paroleConnesse = this.getAllSimilarWords(paroleConNCaratteri, p, numeroLettere);
			
			//cilco for per le parole destinazione/connesse/simili
			for(String pConn : paroleConnesse) {
				
				if(!p.equals(pConn)){
				this.graph.addEdge(p, pConn);
				}
			}
			}
//			this.addEdges();
		
		System.out.println(String.format("grafo creato: %d vertici, %d archi\n", graph.vertexSet().size(), graph.edgeSet().size()));
	}

	
	public int getVertex() {
		return this.graph.vertexSet().size();
	}

	public int getEdges() {
		return this.graph.edgeSet().size();
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

		List<String> vicini = new ArrayList<String>();
		
		vicini = Graphs.neighborListOf(this.graph, parolaInserita);
		
		
		System.err.println("displayNeighbours " + vicini.toString());
		return vicini;
	}

	public String findMaxDegree() {
		int gradoMax = 0;
		String pTemp= "nessuno";
					   //potevamo usare anche la lista paroleConNCaratteri
		for(String p : graph.vertexSet()) {
			int gradoP = this.graph.outDegreeOf(p);
			if(gradoMax < gradoP) {
				gradoMax = gradoP;
				pTemp = p;
				
			}
		}
		
		System.err.println("findMaxDegree" + gradoMax);
		if(gradoMax!=0) {
		return String.format("\nla parola (vertice) con grado massimo (%d) è %s e i suoi vicini sono:\n%s", gradoMax, pTemp, this.displayNeighbours(pTemp).toString());
		}
		return "vertice non presente";
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
	
	public void setGraph(Graph<String, DefaultEdge> graph) {
		this.graph = graph;
	}


	public List<String> getAllSimilarWords(List<String> parole, String parola, int numeroLettere) {

		List<String> paroleSimili = new ArrayList<String>();
		for (String pSimile : parole) {
			if (oneDistance(parola, pSimile))
				paroleSimili.add(pSimile);
		}

		return paroleSimili;
}
	
	public boolean oneDistance(String first, String second) {

		if (first.length() != second.length())
			throw new RuntimeException("Le due parole hanno una lunghezza diversa.");

		int distance = 0;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i))
				distance++;
		}

		if (distance == 1)
			return true;
		else
			return false;
	}

}
