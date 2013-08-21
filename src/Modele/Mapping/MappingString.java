package Modele.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import Modele.Colonne;
import Modele.DataAuditModele;

public class MappingString extends Mapping {
	
	private String[] tabValeursIncorrects = {"null", "not given", "not applicable", "...", ".", "Unknown", "(not given)", "N/A", "Null", "NULL", "(not specified)", "Not Given", "(Unknown)"};
	
	private ArrayList<ValeurIncorrecteString> valeursIncorrectes;
	
	public ArrayList<ValeurIncorrecteString> getValeursIncorrectes() {
		return valeursIncorrectes;
	}

	public void setValeursIncorrectes(
			ArrayList<ValeurIncorrecteString> valeursIncorrectes) {
		this.valeursIncorrectes = valeursIncorrectes;
	}

	public void setTabValeursIncorrects(String[] tabValeursIncorrects) {
		this.tabValeursIncorrects = tabValeursIncorrects;
	}

	public String[] getTabValeursIncorrects() {
		return tabValeursIncorrects;
	}

	public void settabValeursIncorrects(String[] tabValeursIncorrects) {
		this.tabValeursIncorrects = tabValeursIncorrects;
	}
	
	public MappingString(){
		super();
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteString>());
	}
	
	public MappingString(int id, String nom, Classe classe, String nomTable, Connection co){
		super(id, nom, classe, nomTable, co);
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteString>());
	}

	public void arrayValeursIncorrects(Colonne colonne){
		
		this.setColonne(colonne);
		this.getColonne().setClasse(this.getClasse());
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteString>());
		
		if (this.getId() == 0){
			this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteString>());
		}
		else if (this.getId() == 1 || this.getId() == 18){
				
				 String sql = "select `" + colonne.getNomColonne() + "`, count(`" + colonne.getNomColonne() + "`) as cnt ";
					sql +=	"from " + this.getNomTable() + " ";
					sql +=	"group by `" + colonne.getNomColonne() + "` ";
					sql += "having count(`" + colonne.getNomColonne() + "`) > 1 ";
					sql +=	"order by cnt desc";
			
					ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
			
				
					try {
						
						while(resultat.next()){
							
							this.getValeursIncorrectes().add(new ValeurIncorrecteString(resultat.getString(1), resultat.getInt(2), colonne.getNbLignesTotales()));

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			/*
			else if(this.getId() == 15){
				
			}
			
			else if(this.getId() == 16){
				
			}
			*/
			else {
				 String sql = "select `" + colonne.getNomColonne() + "`, count(`" + colonne.getNomColonne() + "`) as cnt ";
					sql +=	"from " + this.getNomTable() + " ";
					sql +=	"group by `" + colonne.getNomColonne() + "` ";
					sql +=	"order by cnt desc";
			
					ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);	
				
					try {
						
						while(resultat.next()){
							
							for (int i = 0; i < this.getTabValeursIncorrects().length; i++){
								if (this.getTabValeursIncorrects()[i].equals(resultat.getString(1))){
									this.getValeursIncorrectes().add(new ValeurIncorrecteString(resultat.getString(1), resultat.getInt(2), colonne.getNbLignesTotales()));
								}
									
							}
						
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		
	}
		
	public String[] transformerListeTab(){
		
		String[] tab = new String[this.getValeursIncorrectes().size()*3];
		int j = 0;
		for (int i = 0; i < this.getValeursIncorrectes().size()*3; i+=3){
			tab[i] = "" + this.getValeursIncorrectes().get(j).getValeur();
			tab[i+1] = "" + this.getValeursIncorrectes().get(j).getQuantite();
			tab[i+2] = this.getValeursIncorrectes().get(j).getPourcentage();
			j++;
		}
		
		return tab;
	}
	
	public int calculerValeursIncorrectes(){
		int nbValeursIncorrectes = 0;
		for (int i = 0; i < this.getValeursIncorrectes().size(); i ++){
			nbValeursIncorrectes += this.getValeursIncorrectes().get(i).getQuantite();
		}
		
		if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
			nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
		}
		return nbValeursIncorrectes;
	}

}
