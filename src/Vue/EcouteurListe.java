package Vue;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Modele.Colonne;
import Modele.Mapping;

public class EcouteurListe implements ListSelectionListener {
	
	DataAuditPanel vue;
	
	EcouteurListe(DataAuditPanel vue){
		this.vue = vue;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		Object source = e.getSource();
		
		if (source == vue.getListeColonne()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			vue.getValue().setText(colonneChoisie.getNomColonne());
			vue.getStorage().setText(colonneChoisie.getTypeDeDonnee());
			vue.getFilledEntries().setText("" + colonneChoisie.getNbCasesRemplies());
			vue.getEmptyEntries().setText("" + colonneChoisie.getNbCasesVides());
			vue.getMappingValue().setText(colonneChoisie.getMapping().getNom());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + colonneChoisie.getPourcentagesCasesRemplies());
			vue.getSelectValue().setSelected(colonneChoisie.isSelectionnee());
			vue.getCBMapping().setSelectedIndex(colonneChoisie.getMapping().getId());
			vue.getListVF().setListData(vue.getDAcontroller().getDAModele().getTabColonne()[vue.getListeColonne().getSelectedIndex()].getValeursListe());
			vue.panelMapping.setVisible(true);
			vue.panelMapping2.setVisible(true);

		}
		
		else if (source == vue.getCBMapping()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			Mapping mappingChoisi = vue.getCBMapping().getSelectedValue();
			
			colonneChoisie.setMapping(mappingChoisi);
			
			colonneChoisie.setNbCasesIncorrectes(mappingChoisi.CalculerValeursIncorrects(colonneChoisie, vue.getDAcontroller().getDAModele().getNomTable(), vue.getDAcontroller().getDAModele().getConnexion()));
			
			colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
			
			
			vue.getMappingValue().setText(colonneChoisie.getMapping().getNom());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + colonneChoisie.getPourcentagesCasesRemplies());
		}
		
	}

}
