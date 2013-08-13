package Modele.Mapping;

public class ValeurIncorrecteString {
	
	private String valeur;
	
	private int quantite;

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public ValeurIncorrecteString(String valeur, int quantite){
		this.setValeur(valeur);
		this.setQuantite(quantite);
	}

}
