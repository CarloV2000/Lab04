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

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Corso>resultCorso = new ArrayList<Corso>();
			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				resultCorso.add(c);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}
			st.close();
			rs.close();
			conn.close();
			return resultCorso;			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiDataMatricola(int matricola){
		List<Corso> result = new ArrayList<Corso>();
		
		String sql = "SELECT c.* "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins AND i.matricola= ? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				result.add(c);
			}
			st.close();
			rs.close();
			conn.close();
			return result;			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
