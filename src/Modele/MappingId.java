package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MappingId extends Mapping {
	
	Colonne colonne;
	String nomTable;
	Connection co;
	
	public MappingId(){
		super();
	}
	
	public MappingId(Colonne colonne, String nomTable, Connection co){
		this.colonne = colonne;
		this.nomTable = nomTable;
		this.co = co;
	}
	
	
	
	public Colonne getColonne() {
		return colonne;
	}



	public void setColonne(Colonne colonne) {
		this.colonne = colonne;
	}



	public String getNomTable() {
		return nomTable;
	}



	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}



	public Connection getCo() {
		return co;
	}



	public void setCo(Connection co) {
		this.co = co;
	}



	public int lignesIncorrectes() throws SQLException{
		
		 String sql = "select count(" + colonne.getNomColonne() + ") as cnt ";
			sql +=	"from " + nomTable + " ";
			sql +=	"group by " + colonne.getNomColonne() + " ";
			sql += "having count(" + colonne.getNomColonne() + ") > 1 ";
			sql +=	"order by cnt desc";
	
	ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
	
	int nbValeursIncorrectes = 0;
		
	while(resultat.next()){
			
		nbValeursIncorrectes += resultat.getInt(1) - 1;
			
	}
	
	return nbValeursIncorrectes;
			
	}

}
