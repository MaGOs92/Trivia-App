package Modele.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import Modele.Colonne;
import Modele.DataAuditModele;

public class MappingINT extends Mapping {
	
	private ArrayList<ValeurIncorrecteINT> valeursIncorrectes;
	
	public ArrayList<ValeurIncorrecteINT> getValeursIncorrectes() {
		return valeursIncorrectes;
	}

	public void setValeursIncorrectes(
			ArrayList<ValeurIncorrecteINT> valeursIncorrectes) {
		this.valeursIncorrectes = valeursIncorrectes;
	}
	
	public MappingINT(){
		super();
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteINT>());
	}

	public MappingINT(int id, String nom, Classe classe, String nomTable, Connection co){
		super(id, nom, classe, nomTable, co);
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteINT>());
	}
	
	public void arrayValeursIncorrects(Colonne colonne){
		
		this.setColonne(colonne);
		this.getColonne().setClasse(this.getClasse());
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteINT>());
				
			if (this.getId() == 1 || this.getId() == 18){
				
				 String sql = "select `" + colonne.getNomColonne() + "`, count(`" + colonne.getNomColonne() + "`) as cnt ";
					sql +=	"from " + this.getNomTable() + " ";
					sql +=	"group by `" + colonne.getNomColonne() + "` ";
					sql += "having count(`" + colonne.getNomColonne() + "`) > 1 ";
					sql +=	"order by cnt desc";
			
					ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
			
				
					try {
						
						while(resultat.next()){
							
							this.getValeursIncorrectes().add(new ValeurIncorrecteINT(resultat.getInt(1), resultat.getInt(2), colonne.getNbLignesTotales()));

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		
	}
	
	public void fourchette(int min, int max){
		
		this.setValeursIncorrectes(new ArrayList<ValeurIncorrecteINT>());
		
		 String sql = "select `" + this.getColonne().getNomColonne() + "`, count(`" + this.getColonne().getNomColonne() + "`) as cnt ";
			sql +=	"from " + this.getNomTable() + " ";
			sql +=	"group by `" + this.getColonne().getNomColonne() + "` ";
			sql += "having `" + this.getColonne().getNomColonne() + "` < " + min + " ";
			sql += "or `" + this.getColonne().getNomColonne() + "` > " + max + " ";
			sql +=	"order by cnt desc";
			
		ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
		try {
			
			while(resultat.next()){
				
				this.getValeursIncorrectes().add(new ValeurIncorrecteINT(resultat.getInt(1), resultat.getInt(2), this.getColonne().getNbLignesTotales()));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
