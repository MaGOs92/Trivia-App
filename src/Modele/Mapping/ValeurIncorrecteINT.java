package Modele.Mapping;

public class ValeurIncorrecteINT {
	
	private int valeur;
	
	private int quantite;

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public ValeurIncorrecteINT(int valeur, int quantite){
		this.setValeur(valeur);
		this.setQuantite(quantite);
	}

}
