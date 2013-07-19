package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
				vue.getListeColonne().setForeground( Color.red );
			}

			vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
			
			vue.getRepporting().setEnabled(true);

				
		}
		
		else if (source == vue.getDeselectAll()){
			
			for (int i = 0; i < vue.getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
				vue.getListeColonne().setSelectedIndex(i);
				vue.getListeColonne().getSelectedValue().setSelectionnee(false);
				vue.getSelectValue().setSelected(vue.getListeColonne().getSelectedValue().isSelectionnee());
				vue.getListeColonne().setForeground( Color.black );
			}
			vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
			
			vue.getRepporting().setEnabled(false);

		}
		
		else if (source == vue.getRepporting()){
				try {
					vue.getDAcontroller().getDAModele().genererFichierCSV(vue.getDAcontroller().getDAModele().dataAudit(), vue.getDAcontroller().getPathFichier());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
	}
	
}
