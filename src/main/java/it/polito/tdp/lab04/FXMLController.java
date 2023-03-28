/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorso;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultati;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	if(this.cmbCorso.getValue()==null) {
    		String inputMatricola = this.txtMatricola.getText();
    		String inputNome = this.txtNome.getText();
    		String inputCognome = this.txtCognome.getText();
    		int inputMatricolaNum;
    		String result = "";
    		try {
    			inputMatricolaNum = Integer.parseInt(inputMatricola);
    		}catch(IllegalArgumentException e) {
    			this.txtRisultati.setText("Errore nell'inserimento dei dati: la matricola deve essere un numero!");
    			return;
    		}
    		for(Corso c : this.model.getCorsiDataMatricola(inputMatricolaNum)) {
    			result += c.toString()+'\n';
    		}
    		this.txtRisultati.setText(result);
    	}
    	else {//questa parte contiene errori: da rivedere!!!
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
    		Corso c = this.cmbCorso.getValue();
    		
    		if(model.loStudenteEIscrittoAlCorso(matricola, c))
    			this.txtRisultati.setText("Lo studente è iscritto al corso!");
    		else
    			this.txtRisultati.setText("Lo studente NON è iscritto al corso!");
    		
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	Corso input = this.cmbCorso.getValue();
    	String result = "";
    	if(input==null) {
    		this.txtRisultati.clear();
    		this.txtRisultati.setText("Errore: selezionare un corso!");
    		return;
    	}
    	for(Studente s : this.model.getStudentiDatoCorso(input.getCodins())) {
    		result+=s.toString()+'\n';
    	}
    	this.txtRisultati.clear();
    	this.txtRisultati.appendText(result);
    	
    }

    @FXML
    void doInserimentoAutomatico(ActionEvent event) {
    	String input = this.txtMatricola.getText();
    	int inputNum;
    	try {
    		inputNum = Integer.parseInt(input.strip());
    	}catch(NumberFormatException e){
    		this.txtRisultati.clear();
    		this.txtRisultati.setText("Inserire un numero!");
    		return;
    	}
    		for(Studente s : model.getTuttiGliStudenti()) {
    			if(s.getMatricola()==inputNum) {
    				this.txtNome.setText(s.getNome());
    				this.txtCognome.setText(s.getCognome());
    			}
    		}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtRisultati.clear();

    }

    @FXML
    void initialize() {
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(Corso c : this.model.getTuttiICorsi())
        	this.cmbCorso.getItems().add(c);
    }

}

