package Modele;

public class Colonne {
	
	private boolean selectionnee;
	
	private int id;
	
	private Mapping mapping;
	
	private int nbLignesTotales;

	private String nomColonne;
	
	private String typeDeDonnee;
	
	private int nbCasesIncorrectes;
	
	private int nbCasesRemplies;
	
	private int nbCasesVides;
	
	private float pourcentagesCasesRemplies;
	
	private String[] valeursFrequentes;
	
	private String[] valeursListe;
	
	Colonne(int id, String nomColonne, String typeDeDonnee, int nbCasesRemplies, int nbCasesVides, int nbLignesTotales, String[] valeursFrequentes, String[] valeursListe){
		this.setId(id);
		this.setNomColonne(nomColonne);
		this.setTypeDeDonnee(typeDeDonnee);
		this.setNbLignesTotales(nbLignesTotales);
		this.setNbCasesRemplies(nbCasesRemplies);
		this.setNbCasesVides(nbCasesVides);
		this.setValeursFrequentes(valeursFrequentes);
		this.setValeursListe(valeursListe);
		this.setSelectionnee(false);
		this.setMapping(new Mapping(0, "None"));
		this.setPourcentagesCasesRemplies(this.calculerPoucentage());
	}
	
	public String toString(){
		String toString = this.getNomColonne();	
		return toString;
	}
	
	public float calculerPoucentage(){
		
		float resultat;
		float total = this.getNbLignesTotales();
		float remplies = this.getNbCasesRemplies();
		float incorrects = this.getNbCasesIncorrectes();
		
		resultat = ((remplies - incorrects)/total) * 100;
		
		return resultat;

	}
	
	public String afficherValeursFrequentes(){
		
		String valeursFrequentes = "";
		
		for (int i = 0; i < 3; i ++){
			valeursFrequentes += this.getValeursFrequentes()[i] + ";";
		}
		
		
		return valeursFrequentes;
	}
	
	public String[] getValeursListe() {
		return valeursListe;
	}

	public void setValeursListe(String[] valeursListe) {
		this.valeursListe = valeursListe;
	}
	
	

	public int getNbLignesTotales() {
		return nbLignesTotales;
	}

	public void setNbLignesTotales(int nbLignesTotales) {
		this.nbLignesTotales = nbLignesTotales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
