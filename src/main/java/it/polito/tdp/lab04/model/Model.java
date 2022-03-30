package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;


public class Model {
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO=new CorsoDAO();
		this.studenteDAO=new StudenteDAO();
	}
	
    public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso) ;
	}
	
    public List<Corso> getTuttiICorsi(){
    	return this.corsoDAO.getTuttiICorsi();
    }

    public Studente getStudente(int matricola) {
    	return this.studenteDAO.getStudente(matricola);
    }
    
    public List<String> getCorsiStudente(Studente studente) {
    	return this.studenteDAO.getCorsiStudente(studente);
    }
    
    public Corso getCorso(String codins) {
    	return this.corsoDAO.getCorso(codins);
    }
	
    public Studente getIscrizione(int matricola, String codins) {
    	return this.studenteDAO.getIscrizione(matricola, codins);
    }
    
    public Corso getCorsoByNome(String nome) {
    	return this.corsoDAO.getCorsoByNome(nome);
    }
}
