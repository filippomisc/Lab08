package it.polito.tdp.dizionariograph.model;

import java.util.*;

public class Utilita {
	
	public static List<String> getAllSimilarWords(List<String> parole, String parola, int numeroLettere) {

		List<String> paroleSimili = new ArrayList<String>();
		for (String pSimile : parole) {
			if (oneDistance(parola, pSimile))
				paroleSimili.add(pSimile);
		}

		return paroleSimili;
}
	
	public static boolean oneDistance(String first, String second) {

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
