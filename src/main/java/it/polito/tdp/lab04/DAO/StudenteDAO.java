package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		String sql="SELECT * "
				+ "FROM studente "
				+ "WHERE matricola=?";
		Studente s = null;
		try {
			
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery(); 
			
			while(rs.next()) {
			s=new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			
			st.close();
			rs.close();
			conn.close();
			return s;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getCorsiStudente(Studente studente) {
		String sql="SELECT codins "
				+ "FROM iscrizione "
				+ "WHERE matricola=?";
		
		List<String> result=new LinkedList<String>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs=st.executeQuery(); 
			
			while(rs.next()) {
			result.add(rs.getString("codins"));
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public Studente getIscrizione(int matricola, String codins) {
		String sql="SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM iscrizione i, studente s "
				+ "WHERE i.matricola=s.matricola AND codins=? AND s.matricola=?";  //specifica s.matricola perchè la matricola c'è in entrambe le tabelle
				
		Studente s=null;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, codins);
			st.setInt(2, matricola);
			ResultSet rs=st.executeQuery(); 
				
			while(rs.next()) {
			s=new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			
			st.close();
			rs.close();
			conn.close();
			return s;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
}
