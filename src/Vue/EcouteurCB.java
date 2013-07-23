package Vue;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;






import Modele.Colonne;

public class EcouteurCB implements ActionListener {
	
	DataAuditPanel vue;
	
	EcouteurCB(DataAuditPanel vue){
		this.vue = vue;
	}

	
	
public DataAuditPanel getVue() {
		return vue;
	}



	public void setVue(DataAuditPanel vue) {
		this.vue = vue;
	}



public void actionPerformed(ActionEvent e){
	
	Object source = e.getSource();
	
	if (source == vue.getSelectValue()){
	Colonne colonneChoisie =  vue.getListeColonne().getSelectedValue();
	colonneChoisie.setSelectionnee(vue.getSelectValue().isSelected());
	vue.getListeColonne().setSelectedIndex(vue.getListeColonne().getSelectedIndex());
	
	vue.getListeColonne().setCellRenderer(new MyCellRenderer(vue));
		
	vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
	
	if (vue.getDAcontroller().getDAModele().getNbLignesSelectionnee() == 0)
		vue.getRepporting().setEnabled(false);
	else
		vue.getRepporting().setEnabled(true);
	
	}

	}

}


class MyCellRenderer extends JLabel implements ListCellRenderer<Colonne> {
	
	private static final long serialVersionUID = 1L;
	
	public MyCellRenderer(DataAuditPanel DAPanel) {
        setOpaque(true);
    }

	public Component getListCellRendererComponent(
			JList<? extends Colonne> arg0, Colonne arg1, int arg2,
			boolean arg3, boolean arg4) {
		
		 setText(arg1.toString());
	        
	        Color foreground = Color.BLACK;
	        Color background = Color.WHITE;
	             
	        if (arg3){
	        	foreground = arg0.getSelectionForeground();
	        	background = arg0.getSelectionBackground();
	        }
	        
	        for (int i = 0; i < arg0.getModel().getSize() ; i++){
	        	if (arg1.isSelectionnee())
	        		foreground = Color.RED;
	        }
	        
	        setForeground(foreground);
	        setBackground(background);
	        setEnabled(arg0.isEnabled());
	        setFont(arg0.getFont());
	        return this;
	}
}
