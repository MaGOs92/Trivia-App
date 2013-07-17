package Modele;

public class Colonne {
	
	private boolean selectionnee;
	
	private Mapping mapping;

	private String nomColonne;
	
	private String typeDeDonnee;
	
	private int nbCasesIncorrectes;
	
	private int nbCasesRemplies;
	
	private int nbCasesVides;
	
	private float pourcentagesCasesRemplies;
	
	private String[] valeursFrequentes;
	
	Colonne(String nomColonne, String typeDeDonnee, int nbCasesRemplies, int nbCasesVides, int nbLignesTotales, String[] valeursFrequentes){
		this.setNomColonne(nomColonne);
		this.setTypeDeDonnee(typeDeDonnee);
		this.setNbCasesRemplies(nbCasesRemplies);
		this.setNbCasesVides(nbCasesVides);
		this.setPourcentagesCasesRemplies(((float)getNbCasesRemplies()-(float)getNbCasesIncorrectes())/(float)nbLignesTotales*(float)100);
		this.setValeursFrequentes(valeursFrequentes);
		this.setSelectionnee(false);
		this.setMapping(new Mapping(0, "None", ""));
	}
	
	public String toString(){
		String toString = this.getNomColonne();	
		return toString;
	}
	
	public String afficherValeursFrequentes(){
		
		String valeursFrequentes = "";
		
		for (int i = 0; i < 3; i ++){
			valeursFrequentes += this.getValeursFrequentes()[i] + ";";
		}
		
		
		return valeursFrequentes;
	}

	
	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public int getNbCasesIncorrectes() {
		return nbCasesIncorrectes;
	}

	public void setNbCasesIncorrectes(int nbCasesIncorrectes) {
		this.nbCasesIncorrectes = nbCasesIncorrectes;
	}

	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}

	public String getNomColonne() {
		return nomColonne;
	}

	public void setNomColonne(String nomColonne) {
		this.nomColonne = nomColonne;
	}

	public String getTypeDeDonnee() {
		return typeDeDonnee;
	}

	public void setTypeDeDonnee(String typeDeDonnee) {
		this.typeDeDonnee = typeDeDonnee;
	}

	public int getNbCasesRemplies() {
		return nbCasesRemplies;
	}

	public void setNbCasesRemplies(int nbCasesRemplies) {
		this.nbCasesRemplies = nbCasesRemplies;
	}

	public int getNbCasesVides() {
		return nbCasesVides;
	}

	public void setNbCasesVides(int nbCasesVides) {
		this.nbCasesVides = nbCasesVides;
	}

	public float getPourcentagesCasesRemplies() {
		return pourcentagesCasesRemplies;
	}

	public void setPourcentagesCasesRemplies(float pourcentagesCasesRemplies) {
		this.pourcentagesCasesRemplies = pourcentagesCasesRemplies;
	}

	public String[] getValeursFrequentes() {
		return valeursFrequentes;
	}

	public void setValeursFrequentes(String[] valeursFrequentes) {
		this.valeursFrequentes = valeursFrequentes;
	}
	
}
