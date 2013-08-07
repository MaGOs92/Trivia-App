package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modele.Colonne;
import Modele.ImportationModele;

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
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee() && vue.getDAcontroller().getDAModele().getTabColonne()[i].getClasse() == "None"){
					j++;
				}
			}
			
			Colonne[] tabClasse = new Colonne[j];			
			
			j = 0;
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++){
				if (vue.getDAcontroller().getDAModele().getTabColonne()[i].isSelectionnee() && vue.getDAcontroller().getDAModele().getTabColonne()[i].getClasse() == "None"){
					tabClasse[j] = vue.getDAcontroller().getDAModele().getTabColonne()[i];
					j++;
				}
			}
			
			if (tabClasse.length != 0){
				SaisieClasse classe = new SaisieClasse(tabClasse);
			}
			
			Modele.PDFGraphiques.writeChartToPDF(nomDuFichier, vue.getDAcontroller().getDAModele());
			
			Object[] options = { "Exit the program", "Return to mapping menu" };
			int option = JOptionPane.showOptionDialog(null, "A new PDF reporting has been generated : " + nomDuFichier, "DataAudit report generated",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
			
			if (option == JOptionPane.YES_OPTION){
				if (!vue.CBKeepTable.isSelected()){
					ImportationModele.deleteTable(vue.getDAcontroller().getCo() , vue.getDAcontroller().getDAModele().getNomTable());
				}
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
			vue.getPourcentage().setText("" + colonneChoisie.getPourcentagesCasesRemplies() + "%");
			
		}
		
		else if (source == vue.getRemove()){
			
			Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
			colonneChoisie.setKeepOrRemove(false);
			colonneChoisie.setNbCasesIncorrectesKR(colonneChoisie.calculerRemove());
			colonneChoisie.setNbCasesIncorrectes(colonneChoisie.getNbCasesIncorrectesMapping() + colonneChoisie.getNbCasesIncorrectesKR());
			colonneChoisie.setPourcentagesCasesRemplies(colonneChoisie.calculerPoucentage());
			vue.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			vue.getPourcentage().setText("" + colonneChoisie.getPourcentagesCasesRemplies() + "%");
		}
		
		else if (source == vue.getBack()){
			
			vue.getDAcontroller().getFenetre().setVisible(false);
			vue.getDAcontroller().getCoImpControler().showImp();
			
		}
		
	}
	
}
