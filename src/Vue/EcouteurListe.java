package Vue;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modele.Colonne;
import Modele.ValeurRetiree;
import Modele.Mapping.MappingINT;
import Modele.Mapping.MappingString;

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
			if (colonneChoisie.isStringValues()){
				vue.add(vue.panelMappingString, BorderLayout.EAST);
				vue.panelMappingINT.setVisible(false);
				vue.panelMappingString.setVisible(true);
				vue.getMappingValue().setText(colonneChoisie.getMappingString().getNom());
				vue.getCBMappingString().setSelectedIndex(colonneChoisie.getMappingString().getId());
			}
			
			else{
				vue.add(vue.panelMappingINT, BorderLayout.EAST);
				vue.panelMappingString.setVisible(false);
				vue.panelMappingINT.setVisible(true);
				vue.getMappingValue().setText(colonneChoisie.getMappingINT().getNom());
				vue.getCBMappingINT().setSelectedIndex(colonneChoisie.getMappingINT().getId());
			}
			
			vue.getClasseValue().setText(colonneChoisie.getClasse().getNom());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
			vue.getSelectValue().setSelected(colonneChoisie.isSelectionnee());
			String[] dataJListVF = Arrays.copyOf(vue.getDAcontroller().getDAModele().getTabColonne()[vue.getListeColonne().getSelectedIndex()].getValeursListe().toArray(), vue.getDAcontroller().getDAModele().getTabColonne()[vue.getListeColonne().getSelectedIndex()].getValeursListe().size(), String[].class);
			vue.getListVF().setListData(dataJListVF);
			if (colonneChoisie.isKeepOrRemove()){
				vue.getKeep().setSelected(true);
				vue.getRemove().setSelected(false);
			}
			else{
				vue.getKeep().setSelected(false);
				vue.getRemove().setSelected(true);
			}
			vue.panelMapping.setVisible(true);
		}
		
		else if (source == vue.getCBMappingString()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			
			MappingString mappingChoisi = vue.getCBMappingString().getSelectedValue();
			
			colonneChoisie.setMappingString(mappingChoisi);
			
			mappingChoisi.arrayValeursIncorrects(colonneChoisie);
			
			colonneChoisie.setNbCasesIncorrectesMapping(mappingChoisi.calculerValeursIncorrectes());
			
			colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			
			colonneChoisie.setPourcentagesCasesRemplies(Math.round(colonneChoisie.calculerPoucentage()));
			
			vue.getMappingValue().setText(colonneChoisie.getMappingString().getNom());
			vue.getClasseValue().setText(colonneChoisie.getClasse().getNom());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
		}
		
		else if (source == vue.getCBMappingINT()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			MappingINT mappingChoisi = vue.getCBMappingINT().getSelectedValue();
			
			colonneChoisie.setMappingINT(mappingChoisi);
			
			mappingChoisi.arrayValeursIncorrects(colonneChoisie);
			
			colonneChoisie.setNbCasesIncorrectesMapping(mappingChoisi.calculerValeursIncorrectes());
			
			colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			
			colonneChoisie.setPourcentagesCasesRemplies(Math.round(colonneChoisie.calculerPoucentage()));

			vue.getMappingValue().setText(colonneChoisie.getMappingINT().getNom());
			vue.getClasseValue().setText(colonneChoisie.getClasse().getNom());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
		}
		
		else if (source == vue.getListVF()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			
			colonneChoisie.setValeursListeSelectionnees(new ArrayList<ValeurRetiree>());
			
			int [] selectedInd = vue.getListVF().getSelectedIndices();
			for (int i = 0; i < selectedInd.length; i++) {
				colonneChoisie.getValeursListeSelectionnees().add(new ValeurRetiree(vue.getListVF().getModel().getElementAt(selectedInd[i])));
				}
			
			if (colonneChoisie.isKeepOrRemove()){
				colonneChoisie.setNbCasesIncorrectesKR(colonneChoisie.calculerKeep());
				colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			}
			else{
				colonneChoisie.setNbCasesIncorrectesKR(colonneChoisie.calculerRemove());
				colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			}
					
			colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
			
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
		}
		
	}

}
