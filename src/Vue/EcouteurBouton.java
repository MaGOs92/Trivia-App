package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modele.Colonne;
import Modele.ConnexionModele;
import Modele.ImportationModele;
import Modele.PDFGraphiques;
import Modele.Mapping.MappingINT;

public class EcouteurBouton implements ActionListener {
	
	DataAuditPanel vue;
	
	EcouteurBouton(DataAuditPanel vue){
		this.vue = vue;
	}

public void actionPerformed(ActionEvent e){
	
	Object source = e.getSource();

		if (source == vue.getSelectAll()){
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
				vue.getListeColonne().setSelectedIndex(i);
				vue.getListeColonne().getSelectedValue().setSelectionnee(true);
				vue.getSelectValue().setSelected(vue.getListeColonne().getSelectedValue().isSelectionnee());
				vue.getListeColonne().setForeground( Color.BLUE );
			}

			vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
			
			vue.getRepporting().setEnabled(true);

				
		}
		
		else if (source == vue.getDeselectAll()){
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
				vue.getListeColonne().setSelectedIndex(i);
				vue.getListeColonne().getSelectedValue().setSelectionnee(false);
				vue.getSelectValue().setSelected(vue.getListeColonne().getSelectedValue().isSelectionnee());
				vue.getListeColonne().setForeground( Color.BLACK );
			}
			vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
			
			vue.getRepporting().setEnabled(false);

		}
		
		else if (source == vue.getRepporting()){
				
			String nomDuFichier = vue.getDAcontroller().getDAModele().nomDuFichierPDF(vue.getDAcontroller().getPathFichier());
			
			SaisieClient client = new SaisieClient(vue.getDAcontroller().getDAModele());
			
			int j = 0;
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++){
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee() && vue.getDAcontroller().getDAModele().getTabColonne()[i].getClasse().getId() == 0){
					j++;
				}
			}
			
			Colonne[] tabClasse = new Colonne[j];			
			
			j = 0;
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++){
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee() && vue.getDAcontroller().getDAModele().getTabColonne()[i].getClasse().getId() == 0){
					tabClasse[j] = vue.getDAcontroller().getDAModele().getTabColonne()[i];
					j++;
				}
			}
			
			if (tabClasse.length != 0){
				for (int i = 0; i < tabClasse.length; i ++){
					SaisieClasse classe = new SaisieClasse(tabClasse[i]);
				}
			}
			
			j = 0;
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++){
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee()){
					j++;
				}
			}
					
			Colonne[] tabClasse2 = new Colonne[j];			
			
			j = 0;
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++){
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee()){
					tabClasse2[j] = vue.getDAcontroller().getDAModele().getTabColonne()[i];
					j++;
				}
			}
			
			MarketabilityPanel MP = new MarketabilityPanel(tabClasse2);
			PromotabilityPanel PP = new PromotabilityPanel(tabClasse2);
			
			PDFGraphiques PDF = new PDFGraphiques(vue.getDAcontroller().getDAModele(), MP.getColonnesSelected(), PP.getColonnesSelected());
			
			Modele.PDFGraphiques.writeChartToPDF(PDF, nomDuFichier, vue.getDAcontroller().getDAModele());
			
			Object[] options = { "Exit the program", "Return to mapping menu" };
			int option = JOptionPane.showOptionDialog(null, "A new PDF reporting has been generated : " + nomDuFichier, "DataAudit report generated",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
			
			if (option == JOptionPane.YES_OPTION){
				if (!vue.CBKeepTable.isSelected()){
					ImportationModele.deleteTable(vue.getDAcontroller().getCo() , vue.getDAcontroller().getDAModele().getNomTable());
				}
				ConnexionModele.closeConnection(vue.getDAcontroller().getCo());
				System.exit(0);
			}

		}
		
		else if (source == vue.getKeep()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			colonneChoisie.setKeepOrRemove(true);
			colonneChoisie.setNbCasesIncorrectesKR(colonneChoisie.calculerKeep());
			colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());	
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
			
		}
		
		else if (source == vue.getRemove()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			colonneChoisie.setKeepOrRemove(false);
			colonneChoisie.setNbCasesIncorrectesKR(colonneChoisie.calculerRemove());
			colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
		}
		
		else if (source == vue.getBack()){
			
			vue.getDAcontroller().getFenetre().setVisible(false);
			vue.getDAcontroller().getCoImpControler().showImp();
			
		}
		
		else if (source == vue.getTriFourchette()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();			
			MappingINT mappingChoisi = vue.getCBMappingINT().getSelectedValue();
			
			String minString = vue.getMinF().getText();
			String maxString = vue.getMaxF().getText();
			
			if (ImportationModele.estUnEntier(minString) && ImportationModele.estUnEntier(maxString)){
				
				int min = Integer.parseInt(minString);
				int max = Integer.parseInt(maxString);
				
				if (min < max){
					
					mappingChoisi.fourchette(min, max);
					colonneChoisie.setNbCasesIncorrectesMapping(0);
					colonneChoisie.setNbCasesIncorrectesMapping(mappingChoisi.calculerValeursIncorrectes());
					colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
					colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
					vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
					vue.getPourcentage().setText("" + Math.round(colonneChoisie.getPourcentagesCasesRemplies()) + "%");
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Minimum value must be less than maximum value.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Minimum and maximum fields must be filled with integers.", "Error", JOptionPane.ERROR_MESSAGE);
			}
					
		}
		
	}
	
}
