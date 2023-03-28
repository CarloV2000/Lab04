package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiStudenti(){
		List<Studente> result = new ArrayList<Studente>();
		
		String sql = "SELECT * FROM studente ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Studente>resultStudente = new ArrayList<Studente>();
			while (rs.next()) {
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				Studente s = new Studente(matricola, cognome, nome, CDS);
				resultStudente.add(s);
			}
			st.close();
			rs.close();
			conn.close();
			return resultStudente;			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public List<Studente> getStudentiDatoCorso(String codins){
		List<Studente> result = new ArrayList<Studente>();
		
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,codins);
			ResultSet rs = st.executeQuery();
			List<Studente>resultStudente = new ArrayList<Studente>();
			while (rs.next()) {

				
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				Studente s = new Studente(matricola, cognome, nome, CDS);
				resultStudente.add(s);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}
			st.close();
			rs.close();
			conn.close();
			return resultStudente;			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
