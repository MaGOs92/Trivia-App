package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EcouteurBouton implements ActionListener {
	
	DataAuditPanel vue;
	
	EcouteurBouton(DataAuditPanel vue){
		this.vue = vue;
	}

public void actionPerformed(ActionEvent e){
	
	Object source = e.getSource();

		if (source == vue.selectAll){
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
				vue.getListeColonne().setSelectedIndex(i);
				vue.getListeColonne().getSelectedValue().setSelectionnee(true);
				vue.getSelectValue().setSelected(vue.getListeColonne().getSelectedValue().isSelectionnee());
				vue.getListeColonne().setForeground( Color.GREEN );
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
			
			Modele.PDFGraphiques.writeChartToPDF(nomDuFichier, vue.getDAcontroller().getDAModele());
			
			Object[] options = { "Exit the program", "Return to mapping menu" };
			int option = JOptionPane.showOptionDialog(null, "A new PDF reporting has been generated : " + nomDuFichier, "DataAudit report generated",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
			
			if (option == JOptionPane.YES_OPTION){
				System.exit(0);
			}

		}
		
	}
	
}
