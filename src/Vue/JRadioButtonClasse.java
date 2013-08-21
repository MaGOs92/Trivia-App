package Vue;

import javax.swing.JRadioButton;

import Modele.Mapping.Classe;

public class JRadioButtonClasse extends JRadioButton {
	
	private static final long serialVersionUID = 1L;
	Classe classe;
	
	
	
	public Classe getClasse() {
		return classe;
	}



	public void setClasse(Classe classe) {
		this.classe = classe;
	}



	public JRadioButtonClasse(String nom, Classe classe){
		super(nom);
		this.setClasse(classe);
	}

}
