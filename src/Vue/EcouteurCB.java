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
	if (vue.getDAcontroller().getDAModele().getTabColonne()[vue.getListeColonne().getSelectedIndex()].isSelectionnee())
		vue.getListeColonne().getComponent(vue.getListeColonne().getSelectedIndex()).setForeground(Color.RED);
	else
		vue.getListeColonne().getComponent(vue.getListeColonne().getSelectedIndex()).setForeground(Color.BLACK);
		
	vue.getlListeColonne().setText("Number of values selected : " + vue.getDAcontroller().getDAModele().getNbLignesSelectionnee());
	
	if (vue.getDAcontroller().getDAModele().getNbLignesSelectionnee() == 0)
		vue.getRepporting().setEnabled(false);
	else
		vue.getRepporting().setEnabled(true);
	
	}

	}

}


class MyCellRenderer extends JLabel implements ListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyCellRenderer() {
        setOpaque(true);
    }
    public Component getListCellRendererComponent(
        JList<Colonne> list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus)
    {
    	
        if (b.equals("1"))
        	setForeground(new Color(83,213,148));
        return this;
    }
}