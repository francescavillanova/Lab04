/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbCorsi"
    private ComboBox<String> cmbCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtStudente"
    private TextField txtStudente; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	
    	String matricola=txtStudente.getText(); 
    	int matricolaNum;  
    	try {    
    		matricolaNum=Integer.parseInt(matricola);   
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisci una matricola numerica");
    		return; 
    	}
    	
    	Studente s=this.model.getStudente(matricolaNum);
    	if(s==null) {
    		txtRisultato.setText("Attenzione, studente non presente!");
    		return;
    	}else {
    		List<String> codici=new LinkedList<String>(model.getCorsiStudente(s));
    		if(codici.isEmpty())
    			txtRisultato.setText("Attenzione, studente non iscritto ad alcun corso");
    		else {
    			for(String c: codici) {
        			txtRisultato.appendText(model.getCorso(c)+"\n");
        		}
    		}
    		
    		
    	}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
        
    	try {
    		if(cmbCorsi.getValue().equals("Corsi")) {
           		txtRisultato.setText("Attenzione, selezionare un corso!");
           		return;
           	}else {
           		Corso c;
           		c=this.model.getCorsoByNome(cmbCorsi.getValue());
           		List<Studente> stud=new LinkedList<Studente>(this.model.getStudentiIscrittiAlCorso(c));
           		if(stud.isEmpty()) {
           			txtRisultato.setText("Attenzione, corso senza iscritti!");
           		}else {
           			for(Studente s: stud ) {
                		txtRisultato.appendText(s+"\n");
                	}
           		}
           		
           	}
    	}catch(NullPointerException e) {
    		txtRisultato.setText("Attenzione, selezionare un corso!");
    		return;
    	}
       	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtRisultato.clear();
        
        if(!cmbCorsi.getValue().equals("Corsi")) {
        	String matricola=txtStudente.getText(); 
         	int matricolaNum;  
         	try {    
         		matricolaNum=Integer.parseInt(matricola);   
         	}catch(NumberFormatException e) {
         		txtRisultato.setText("Inserisci una matricola numerica");
         		return; 
         	}
         	
         	Studente s=this.model.getStudente(matricolaNum);
         	Corso c=this.model.getCorsoByNome(cmbCorsi.getValue());
        	if(s==null) {
        		txtRisultato.setText("Attenzione, studente non presente!");
        		return;
        	}else {
        		Studente ss=this.model.getIscrizione(matricolaNum, c.getCodins());
        		if(ss==null) {
                	txtRisultato.setText("Studente non iscritto a questo corso");
                	
                }else {
                	txtRisultato.setText("Studente già iscritto a questo corso");
                }
        	}
        	      		
                       	
        }
       

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtRisultato.clear();
    	txtStudente.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	cmbCorsi.getSelectionModel().clearSelection();
 
    }
    
    @FXML
    void doVerde(ActionEvent event) {
    	String matricola=txtStudente.getText(); 
    	int matricolaNum;  
    	try {    
    		matricolaNum=Integer.parseInt(matricola);   
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisci una matricola numerica");
    		return; 
    	}
    	
    	Studente s=this.model.getStudente(matricolaNum);
    	if(s==null) {
    		txtRisultato.setText("Attenzione, studente non presente!");
    		return;
    	}else {
    	txtCognome.setText(s.getCognome());
    	txtNome.setText(s.getNome());
    	}
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStudente != null : "fx:id=\"txtStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        
      

    }

    public void setModel(Model model) {
        this.model=model;
          
       	List<Corso> corsi=new LinkedList<Corso>(model.getTuttiICorsi());
      	List<String> nomi=new LinkedList<String>();
      	for(Corso c: corsi) {
      		nomi.add(c.getNome());
      	}
      	nomi.add("Corsi"); //campo vuoto
       
      	ObservableList<String> corsicmb = FXCollections.observableArrayList (nomi); 
       	cmbCorsi.setItems(corsicmb);  //popolare menù a tendina
    	 
        
      }
}

