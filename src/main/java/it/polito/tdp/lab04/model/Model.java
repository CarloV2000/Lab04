package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiGliStudenti(){
		return this.studenteDAO.getTuttiStudenti();
	}
	
	public List<Studente> getStudentiDatoCorso(String codins){
		return this.studenteDAO.getStudentiDatoCorso(codins);
	}
	
	public List<Corso> getCorsiDataMatricola(int matricola){
		return this.corsoDAO.getCorsiDataMatricola(matricola);
	}
	
	public boolean loStudenteEIscrittoAlCorso(int matr, Corso c) {
		boolean a = false;
		List<Corso>listaCorsiIscritto = new ArrayList<Corso>(this.getCorsiDataMatricola(matr));
		if(listaCorsiIscritto.contains(c))
			a = true;
		return a;
	}
	

}
