package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controleur.DataAuditControleur;


public class DataAuditPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	DataAuditControleur DAcontroller;
	
	
	
	JPanel panelColonnes;
	JLabel LColonne;
	JList<?> listeColonne;
	
	JPanel panelMapping;
	JLabel LMapping;
	JComboBox<?> CBMapping;
	static String[] tabMapping = {"ID", "Country", "Company name", "Physical address L1", "Physical Address L2", "Physical Address L3",
		"Physical city", "Physical state", "Zip postal code", "Phone number", "Website", "Industry code (Sic4)", "Industry code (NAICS6)", "Industry code (NAF)",
		"Industry code (APE)", "Descr. Industry code", "Employee at site", "Employee total", "Annual sales", "ID contact", "Title", "Contact first name",
		"Contact last name", "Contact phone", "Contact email", "Internal marketability indicator", "Opt/Out flag"
	};
	
	JButton repporting;
	
	
	
	public DataAuditControleur getDAcontroller() {
		return DAcontroller;
	}

	public void setDAcontroller(DataAuditControleur dAcontroller) {
		DAcontroller = dAcontroller;
	}

	public JPanel getPanelColonnes() {
		return panelColonnes;
	}

	public void setPanelColonnes(JPanel panelColonnes) {
		this.panelColonnes = panelColonnes;
	}

	public JLabel getLColonne() {
		return LColonne;
	}

	public void setLColonne(JLabel lColonne) {
		LColonne = lColonne;
	}

	public JList<?> getListeColonne() {
		return listeColonne;
	}

	public void setListeColonne(JList<?> listeColonne) {
		this.listeColonne = listeColonne;
	}

	public JPanel getPanelMapping() {
		return panelMapping;
	}

	public void setPanelMapping(JPanel panelMapping) {
		this.panelMapping = panelMapping;
	}

	public JLabel getLMapping() {
		return LMapping;
	}

	public void setLMapping(JLabel lMapping) {
		LMapping = lMapping;
	}

	public JComboBox<?> getCBMapping() {
		return CBMapping;
	}

	public void setCBMapping(JComboBox<?> cBMapping) {
		CBMapping = cBMapping;
	}

	public static String[] getTabMapping() {
		return tabMapping;
	}

	public static void setTabMapping(String[] tabMapping) {
		DataAuditPanel.tabMapping = tabMapping;
	}

	public JButton getRepporting() {
		return repporting;
	}

	public void setRepporting(JButton repporting) {
		this.repporting = repporting;
	}

	public DataAuditPanel(DataAuditControleur DAcontroller){
		
		this.setDAcontroller(DAcontroller);
		
		this.setLayout(new BorderLayout());
		// Construction des panels
		setPanelColonnes(new JPanel());
		setPanelMapping(new JPanel());
		
		getPanelColonnes().setLayout(new BorderLayout());
		
		// Construction du panel colonne
		
		String [] data = new String[getDAcontroller().getDAModele().getNbColonnesTotales()];

		LColonne = new JLabel("Select a value to map it. When you are done, you can select the values that you want in the reporting and edit it.");
		
		for (int i = 0; i < getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
		  data[i] = getDAcontroller().getDAModele().getTabColonne()[i].getNomColonne();
		}
		
		listeColonne = new JList(data); //data has type Object[]
		listeColonne.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeColonne.setLayoutOrientation(JList.VERTICAL);
		listeColonne.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(listeColonne);
		listScroller.setPreferredSize(new Dimension(250, 500));
		
		getPanelColonnes().add(getLColonne(), BorderLayout.NORTH);
		getPanelColonnes().add(listScroller, BorderLayout.CENTER);
		
		// Construction du panel mapping
		
		LMapping = new JLabel("Mapping :");
		//CBMapping = new JComboBox<E>(tabMapping);
		
		this.add(getPanelColonnes(), BorderLayout.WEST);
		this.add(getPanelMapping(), BorderLayout.EAST);

	}
	
	public void actionPerformed(ActionEvent e){
		
		
	}
	

}
