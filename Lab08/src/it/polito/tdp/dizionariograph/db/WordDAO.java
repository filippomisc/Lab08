package it.polito.tdp.dizionariograph.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {
  
	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int length) {

		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?";
		List<String> parole = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, length);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				parole.add(res.getString("nome"));
			}

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<String> getAllWordsFixedLengthAndConnected(String p, int length) {
		
		String sql = "SELECT nome FROM parola \n" + 
				"WHERE nome \n" + 
				"LIKE nome=? AND LENGTH(nome) = ?";
		
		List<String> parole = new ArrayList<String>();

		try {
			
			//copiare la parola in un array di char per dividere le lettere
			char[] charParolaDaCercare = p.toCharArray();
			
			/**
			 * fare un ciclo for per sostituire ad ogni lettera del char
			 * uno '_'  per fare la query in sql
			 */
			for(int i=0; i<p.length(); i++) {
				
				//salviamo prima la letteera i-esima in una var temporanea
				//per poi inserirla di nuovo ***
				char temp = charParolaDaCercare[i];
				
						//sostituiamo ad ogni lettera i-esima il carattere'_'
						charParolaDaCercare[i] = '_';
						
						//copiamo la parola che useremo nella query
						String pDaCercare = String.copyValueOf(charParolaDaCercare);
				
				//***
				charParolaDaCercare[i] = temp;
				
				

				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1, pDaCercare);
				st.setInt(2, length);
				
				ResultSet res = st.executeQuery();
				
				while(res.next()) {
					
					String nome = res.getString("nome");
					parole.add(nome);
				}

				
			}
			return parole;
			
		} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error Connection Database");
		}
	}
				


}
