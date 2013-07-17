package Vue;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




import Controleur.DataAuditControleur;
import Modele.Colonne;
import Modele.DataAuditModele;
import Modele.Mapping;


public class DataAuditPanel extends JPanel implements ActionListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	
	DataAuditControleur DAcontroller;
	
	JPanel pInfo;
	JLabel lFile;
	JLabel file;
	JLabel lNumberEntries;
	JLabel numberEntries;
	
	JPanel panelColonnes;
	JLabel lListeColonne;
	JList<Colonne> listeColonne;
	
	JPanel panelMapping;
	JLabel LMapping;
	JList<Mapping> CBMapping;
	JLabel lValue;
	JLabel value;
	JLabel lFilledentries;
	JLabel filledEntries;
	JLabel lEmptyEntries;
	JLabel emptyEntries;
	JLabel lMappingValue;
	JLabel mappingValue;
	JLabel lIncorrectEntries;
	JLabel incorrectEntries;
	JLabel lSelectValue;
	JLabel lPourcentage;
	JLabel pourcentage;
	JCheckBox selectValue;
	JPanel pRepporting;
	JButton repporting;
	JButton selectAll;
	JButton deselectAll;
	
	JPanel panelMapping2;
	
	JPanel valeursfrequentes;
	JPanel VFLabelContainer;
	JLabel lvaleursfrequentes;
	JList<String> listVF;
	
	
	
	public JLabel getlListeColonne() {
		return lListeColonne;
	}

	public void setlListeColonne(JLabel lListeColonne) {
		this.lListeColonne = lListeColonne;
	}

	public JPanel getPanelMapping2() {
		return panelMapping2;
	}

	public void setPanelMapping2(JPanel panelMapping2) {
		this.panelMapping2 = panelMapping2;
	}

	public JPanel getValeursfrequentes() {
		return valeursfrequentes;
	}

	public void setValeursfrequentes(JPanel valeursfrequentes) {
		this.valeursfrequentes = valeursfrequentes;
	}

	public JPanel getVFLabelContainer() {
		return VFLabelContainer;
	}

	public void setVFLabelContainer(JPanel vFLabelContainer) {
		VFLabelContainer = vFLabelContainer;
	}

	public JLabel getLvaleursfrequentes() {
		return lvaleursfrequentes;
	}

	public void setLvaleursfrequentes(JLabel lvaleursfrequentes) {
		this.lvaleursfrequentes = lvaleursfrequentes;
	}

	public JList<String> getListVF() {
		return listVF;
	}

	public void setListVF(JList<String> listVF) {
		this.listVF = listVF;
	}

	public JLabel getlFile() {
		return lFile;
	}

	public void setlFile(JLabel lFile) {
		this.lFile = lFile;
	}

	public JLabel getFile() {
		return file;
	}

	public void setFile(JLabel file) {
		this.file = file;
	}

	public JLabel getlNumberEntries() {
		return lNumberEntries;
	}

	public void setlNumberEntries(JLabel lNumberEntries) {
		this.lNumberEntries = lNumberEntries;
	}

	public JLabel getNumberEntries() {
		return numberEntries;
	}

	public void setNumberEntries(JLabel numberEntries) {
		this.numberEntries = numberEntries;
	}

	public JLabel getlMappingValue() {
		return lMappingValue;
	}

	public void setlMappingValue(JLabel lMappingValue) {
		this.lMappingValue = lMappingValue;
	}

	public JLabel getMappingValue() {
		return mappingValue;
	}

	public void setMappingValue(JLabel mappingValue) {
		this.mappingValue = mappingValue;
	}

	public JLabel getlValue() {
		return lValue;
	}

	public void setlValue(JLabel lValue) {
		this.lValue = lValue;
	}

	public JLabel getValue() {
		return value;
	}

	public void setValue(JLabel value) {
		this.value = value;
	}

	public JLabel getlPourcentage() {
		return lPourcentage;
	}

	public void setlPourcentage(JLabel lPourcentage) {
		this.lPourcentage = lPourcentage;
	}

	public JLabel getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(JLabel pourcentage) {
		this.pourcentage = pourcentage;
	}

	public JPanel getpInfo() {
		return pInfo;
	}

	public void setpInfo(JPanel pInfo) {
		this.pInfo = pInfo;
	}

	public JLabel getlFilledentries() {
		return lFilledentries;
	}

	public void setlFilledentries(JLabel lFilledentries) {
		this.lFilledentries = lFilledentries;
	}

	public JLabel getFilledEntries() {
		return filledEntries;
	}

	public void setFilledEntries(JLabel filledEntries) {
		this.filledEntries = filledEntries;
	}

	public JLabel getlEmptyEntries() {
		return lEmptyEntries;
	}

	public void setlEmptyEntries(JLabel lEmptyEntries) {
		this.lEmptyEntries = lEmptyEntries;
	}

	public JLabel getEmptyEntries() {
		return emptyEntries;
	}

	public void setEmptyEntries(JLabel emptyEntries) {
		this.emptyEntries = emptyEntries;
	}

	public JLabel getlIncorrectEntries() {
		return lIncorrectEntries;
	}

	public void setlIncorrectEntries(JLabel lIncorrectEntries) {
		this.lIncorrectEntries = lIncorrectEntries;
	}

	public JLabel getIncorrectEntries() {
		return incorrectEntries;
	}

	public void setIncorrectEntries(JLabel incorrectEntries) {
		this.incorrectEntries = incorrectEntries;
	}

	public JLabel getlSelectValue() {
		return lSelectValue;
	}

	public void setlSelectValue(JLabel lSelectValue) {
		this.lSelectValue = lSelectValue;
	}

	public JCheckBox getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(JCheckBox selectValue) {
		this.selectValue = selectValue;
	}

	public JPanel getpRepporting() {
		return pRepporting;
	}

	public void setpRepporting(JPanel pRepporting) {
		this.pRepporting = pRepporting;
	}

	public JButton getSelectAll() {
		return selectAll;
	}

	public void setSelectAll(JButton selectAll) {
		this.selectAll = selectAll;
	}

	public JButton getDeselectAll() {
		return deselectAll;
	}

	public void setDeselectAll(JButton deselectAll) {
		this.deselectAll = deselectAll;
	}

	public void setCBMapping(JList<Mapping> cBMapping) {
		CBMapping = cBMapping;
	}

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

	public JList<Colonne> getListeColonne() {
		return listeColonne;
	}

	public void setListeColonne(JList<Colonne> listeColonne) {
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

	public JList<Mapping> getCBMapping() {
		return CBMapping;
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
		
		// panel info
		
		pInfo = new JPanel();
		pInfo.setLayout(new FlowLayout());
		lFile = new JLabel("Audited file :");
		file = new JLabel(this.getDAcontroller().getDAModele().getNomTable());
		lFile.setLabelFor(file);
		lNumberEntries = new JLabel("Total number of entries :");
		numberEntries = new JLabel("" + this.getDAcontroller().getDAModele().getNbLignesTotales());
		lNumberEntries.setLabelFor(numberEntries);
		pInfo.add(lFile);
		pInfo.add(file);
		pInfo.add(lNumberEntries);
		pInfo.add(numberEntries);
		
		// Construction du panel colonne
		
		lListeColonne = new JLabel("Number of values selected : " + this.getDAcontroller().getDAModele().getNbLignesSelectionnee());
		JPanel containerValuesList = new JPanel(new FlowLayout());
		containerValuesList.add(lListeColonne);
		
		listeColonne = new JList<Colonne>(getDAcontroller().getDAModele().getTabColonne()); //data has type Object[]
		listeColonne.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeColonne.setLayoutOrientation(JList.VERTICAL);
		listeColonne.setVisibleRowCount(-1);
		listeColonne.addListSelectionListener(this);
		
		
		JScrollPane listScroller = new JScrollPane(listeColonne);
		listScroller.setPreferredSize(new Dimension(250, 500));
		
		getPanelColonnes().add(containerValuesList, BorderLayout.NORTH);
		getPanelColonnes().add(listScroller, BorderLayout.CENTER);
		
		// Construction du panel mapping

		panelMapping = new JPanel(new SpringLayout());
		
		panelMapping2 = new JPanel(new BorderLayout());
		
		
		// Champ combo
		LMapping = new JLabel("Mapping values :", JLabel.TRAILING);
		panelMapping2.add(LMapping, BorderLayout.NORTH);
		
		String[] tabCombo = new String[this.getDAcontroller().getDAModele().getTabMapping().length];
		
		for (int i = 0; i < this.getDAcontroller().getDAModele().getTabMapping().length; i++){
			tabCombo[i] = this.getDAcontroller().getDAModele().getTabMapping()[i].toString();
		}
		
		CBMapping = new JList<Mapping>(getDAcontroller().getDAModele().getTabMapping());
		CBMapping.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CBMapping.setLayoutOrientation(JList.VERTICAL);
		CBMapping.setVisibleRowCount(-1);
		LMapping.setLabelFor(CBMapping);
		CBMapping.addListSelectionListener(this);
		JScrollPane listScroller2 = new JScrollPane(CBMapping);
		listScroller2.setPreferredSize(new Dimension(150, 300));
		JPanel containerMapList = new JPanel(new FlowLayout());
		containerMapList.add(LMapping);
		panelMapping2.add(containerMapList, BorderLayout.NORTH);
		panelMapping2.add(listScroller2, BorderLayout.CENTER);
		
		
		lValue = new JLabel("Value :", JLabel.TRAILING);
		panelMapping.add(lValue);
		value = new JLabel("");
		lValue.setLabelFor(value);
		panelMapping.add(value);
		
		lFilledentries = new JLabel("Number of filled entries :", JLabel.TRAILING);
		panelMapping.add(lFilledentries);
		filledEntries = new JLabel("");
		lFilledentries.setLabelFor(filledEntries);
		panelMapping.add(filledEntries);
		
		lEmptyEntries = new JLabel("Number of empty entries :", JLabel.TRAILING);
		panelMapping.add(lEmptyEntries);
		emptyEntries = new JLabel("");
		lEmptyEntries.setLabelFor(emptyEntries);
		panelMapping.add(emptyEntries);
		
		lMappingValue = new JLabel("Mapping value :", JLabel.TRAILING);
		panelMapping.add(lMappingValue);
		mappingValue = new JLabel("");
		lMappingValue.setLabelFor(mappingValue);
		panelMapping.add(mappingValue);
		
		lIncorrectEntries = new JLabel("Number of incorrect values :", JLabel.TRAILING);
		panelMapping.add(lIncorrectEntries);
		incorrectEntries = new JLabel("");
		lIncorrectEntries.setLabelFor(incorrectEntries);
		panelMapping.add(incorrectEntries);
		
		lPourcentage = new JLabel("Percentage of correct values :", JLabel.TRAILING);
		panelMapping.add(lPourcentage);
		pourcentage = new JLabel("");
		lPourcentage.setLabelFor(pourcentage);
		panelMapping.add(pourcentage);
		
		lSelectValue = new JLabel("Select this value in the repporting :", JLabel.TRAILING);
		panelMapping.add(lSelectValue);
		selectValue = new JCheckBox();
		lSelectValue.setLabelFor(selectValue);
		selectValue.addActionListener(this);
		panelMapping.add(selectValue);
		
		// Valeurs fréquentes
		/*
		valeursfrequentes = new JPanel(new BorderLayout());
		VFLabelContainer = new JPanel(new FlowLayout());
		lvaleursfrequentes = new JLabel("Frequent values : ");
		VFLabelContainer.add(lvaleursfrequentes);
		
		listVF = new JList<String>(listeColonne.); //data has type Object[]
		listVF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVF.setLayoutOrientation(JList.VERTICAL);
		listVF.setVisibleRowCount(-1);
		listVF.addListSelectionListener(this);
		
		valeursfrequentes.add(VFLabelContainer);
		*/
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(panelMapping,
		                                7, 2, //rows, cols
		                                10, 10,        //initX, initY
		                                10, 10);       //xPad, yPad
		
		
		// Construction du JPanel repporting
		
		JPanel pRepporting = new JPanel();
		JButton repporting = new JButton("Edit Repporting");
		JButton selectAll = new JButton("Select all");
		JButton deselectAll = new JButton("Deselect all");
		
		
		pRepporting.setLayout(new FlowLayout());
		selectAll.addActionListener(this);
		pRepporting.add(selectAll);
		
		deselectAll.addActionListener(this);
		pRepporting.add(deselectAll);
		
		repporting.addActionListener(this);
		pRepporting.add(repporting);
		
		// Population de la fenetre
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(pInfo, BorderLayout.NORTH);
		this.add(getPanelColonnes(), BorderLayout.WEST);
		this.add(panelMapping2, BorderLayout.EAST);
		panelMapping2.setVisible(false);
		this.add(getPanelMapping(), BorderLayout.CENTER);
		panelMapping.setVisible(false);
		this.add(pRepporting, BorderLayout.SOUTH);
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		if (e.getSource() == getListeColonne()){
			
			Colonne colonneChoisie =  getListeColonne().getSelectedValue();
			this.getValue().setText(colonneChoisie.getNomColonne());
			this.getFilledEntries().setText("" + colonneChoisie.getNbCasesRemplies());
			this.getEmptyEntries().setText("" + colonneChoisie.getNbCasesVides());
			this.getMappingValue().setText(colonneChoisie.getMapping().getNom());
			this.getIncorrectEntries().setText("" + colonneChoisie.getNbCasesIncorrectes());
			this.getPourcentage().setText("" + colonneChoisie.getPourcentagesCasesRemplies());
			this.getSelectValue().setSelected(colonneChoisie.isSelectionnee());
			this.getCBMapping().setSelectedIndex(colonneChoisie.getMapping().getId());
			panelMapping.setVisible(true);
			panelMapping2.setVisible(true);
			//System.out.println(this.getDAcontroller().getDAModele().getTabColonne()[getListeColonne().getSelectedIndex()].getMapping().getNom());
		}
		
		if (e.getSource() == getCBMapping()){
			Colonne colonneChoisie =  getListeColonne().getSelectedValue();
			Mapping mappingChoisi = getCBMapping().getSelectedValue();
			
			colonneChoisie.setMapping(mappingChoisi);
			this.getMappingValue().setText(colonneChoisie.getMapping().getNom());
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		
		if (e.getSource() == getSelectAll()){
			for (int i = 0; i < getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
			}
			this.getlListeColonne().setText("Number of values selected : " + this.getDAcontroller().getDAModele().getNbLignesSelectionnee());
		}
		
		if (e.getSource() == getDeselectAll()){
			
			for (int i = 0; i < getDAcontroller().getDAModele().getNbColonnesTotales(); i++) {
				getListeColonne().setSelectedIndex(i);
				getListeColonne().getSelectedValue().setSelectionnee(false);
				this.getSelectValue().setSelected(getListeColonne().getSelectedValue().isSelectionnee());
				getListeColonne().setForeground( Color.black );
			}
			this.getlListeColonne().setText("Number of values selected : " + this.getDAcontroller().getDAModele().getNbLignesSelectionnee());

		}
		
		if (e.getSource() == getSelectValue()){
			Colonne colonneChoisie =  getListeColonne().getSelectedValue();
			colonneChoisie.setSelectionnee(getSelectValue().isSelected());
			this.getlListeColonne().setText("Number of values selected : " + this.getDAcontroller().getDAModele().getNbLignesSelectionnee());
		}
		
		if (e.getSource() == getRepporting()){
			
			System.out.println(this.getDAcontroller().getPathFichier());
			
				try {
					this.getDAcontroller().getDAModele().genererFichierCSV(this.getDAcontroller().getDAModele().dataAudit(), this.getDAcontroller().getPathFichier());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		
	}
	

}
