package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.*;
import Controleur.DataAuditControleur;
import Modele.Colonne;
import Modele.Mapping.MappingINT;
import Modele.Mapping.MappingString;


public class DataAuditPanel extends JPanel {
	
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
	JLabel LMappingString;
	JLabel LMappingINT;
	JList<MappingString> CBMappingString;
	JList<MappingINT> CBMappingINT;
	JButton triFourchette;
	JTextField maxF;
	JTextField minF;
	JLabel lValue;
	JLabel value;
	JLabel lStorage;
	JLabel storage;
	JLabel lFilledentries;
	JLabel filledEntries;
	JLabel lEmptyEntries;
	JLabel emptyEntries;
	JLabel lMappingValue;
	JLabel mappingValue;
	JLabel lClasseValue;
	JLabel classeValue;
	JLabel lIncorrectEntries;
	JLabel incorrectEntries;
	JLabel lSelectValue;
	JLabel lPourcentage;
	JLabel pourcentage;
	JCheckBox selectValue;
	JPanel pRepporting;
	JLabel lChoixRadio;
	ButtonGroup choixRadio;
	JRadioButton keep;
	JRadioButton remove;
	JButton repporting;
	JButton selectAll;
	JButton deselectAll;
	JLabel LKeepTable;
	JCheckBox CBKeepTable;
	JButton back;
	
	JPanel panelMappingString;
	JPanel panelMappingINT;
	
	JPanel VFLabelContainer;
	JLabel lvaleursfrequentes;
	JList<String> listVF;
	
	
	public JTextField getMaxF() {
		return maxF;
	}



	public void setMaxF(JTextField maxF) {
		this.maxF = maxF;
	}



	public JTextField getMinF() {
		return minF;
	}



	public void setMinF(JTextField minF) {
		this.minF = minF;
	}



	public JButton getTriFourchette() {
		return triFourchette;
	}



	public void setTriFourchette(JButton triFourchette) {
		this.triFourchette = triFourchette;
	}



	public JLabel getLMappingINT() {
		return LMappingINT;
	}



	public void setLMappingINT(JLabel lMappingINT) {
		LMappingINT = lMappingINT;
	}



	public JLabel getlClasseValue() {
		return lClasseValue;
	}



	public JList<MappingString> getCBMappingString() {
		return CBMappingString;
	}



	public void setCBMappingString(JList<MappingString> cBMappingString) {
		CBMappingString = cBMappingString;
	}



	public JList<MappingINT> getCBMappingINT() {
		return CBMappingINT;
	}



	public void setCBMappingINT(JList<MappingINT> cBMappingINT) {
		CBMappingINT = cBMappingINT;
	}



	public void setlClasseValue(JLabel lClasseValue) {
		this.lClasseValue = lClasseValue;
	}



	public JLabel getClasseValue() {
		return classeValue;
	}



	public void setClasseValue(JLabel classeValue) {
		this.classeValue = classeValue;
	}



	public JLabel getLKeepTable() {
		return LKeepTable;
	}



	public void setLKeepTable(JLabel lKeepTable) {
		LKeepTable = lKeepTable;
	}



	public JCheckBox getCBKeepTable() {
		return CBKeepTable;
	}



	public void setCBKeepTable(JCheckBox cBKeepTable) {
		CBKeepTable = cBKeepTable;
	}



	public JButton getBack() {
		return back;
	}



	public void setBack(JButton back) {
		this.back = back;
	}



	public JLabel getlListeColonne() {
		return lListeColonne;
	}

	
	
	public JLabel getlStorage() {
		return lStorage;
	}



	public void setlStorage(JLabel lStorage) {
		this.lStorage = lStorage;
	}



	public JLabel getStorage() {
		return storage;
	}



	public void setStorage(JLabel storage) {
		this.storage = storage;
	}



	public void setlListeColonne(JLabel lListeColonne) {
		this.lListeColonne = lListeColonne;
	}

	public JPanel getPanelMappingString() {
		return panelMappingString;
	}



	public void setPanelMappingString(JPanel panelMappingString) {
		this.panelMappingString = panelMappingString;
	}



	public JPanel getPanelMappingINT() {
		return panelMappingINT;
	}



	public void setPanelMappingINT(JPanel panelMappingINT) {
		this.panelMappingINT = panelMappingINT;
	}



	public JLabel getlChoixRadio() {
		return lChoixRadio;
	}



	public void setlChoixRadio(JLabel lChoixRadio) {
		this.lChoixRadio = lChoixRadio;
	}



	public ButtonGroup getChoixRadio() {
		return choixRadio;
	}



	public void setChoixRadio(ButtonGroup choixRadio) {
		this.choixRadio = choixRadio;
	}



	public JRadioButton getKeep() {
		return keep;
	}



	public void setKeep(JRadioButton keep) {
		this.keep = keep;
	}



	public JRadioButton getRemove() {
		return remove;
	}



	public void setRemove(JRadioButton remove) {
		this.remove = remove;
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

	public JLabel getLMappingString() {
		return LMappingString;
	}

	public void setLMappingString(JLabel lMappingString) {
		LMappingString = lMappingString;
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
		
		JLabel lab = new JLabel(new ImageIcon("Img\\logo.png"));
		pInfo.add(lab, FlowLayout.LEFT);
		pInfo.add(lFile);
		pInfo.add(file);
		pInfo.add(lNumberEntries);
		pInfo.add(numberEntries);
		
		// Construction du panel colonne
		
		lListeColonne = new JLabel("Number of fields selected : " + this.getDAcontroller().getDAModele().getNbLignesSelectionnee());
		JPanel containerValuesList = new JPanel(new FlowLayout());
		containerValuesList.add(lListeColonne);
		
		listeColonne = new JList<Colonne>(getDAcontroller().getDAModele().getTabColonne()); //data has type Object[]
		listeColonne.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeColonne.setLayoutOrientation(JList.VERTICAL);
		listeColonne.setVisibleRowCount(-1);
		listeColonne.setSelectedIndex(0);
		listeColonne.setCellRenderer(new MyCellRenderer(this));
		listeColonne.addListSelectionListener(new EcouteurListe(this));
		
		
		JScrollPane listScroller = new JScrollPane(listeColonne);
		listScroller.setPreferredSize(new Dimension(150, 500));
		
		getPanelColonnes().add(containerValuesList, BorderLayout.NORTH);
		getPanelColonnes().add(listScroller, BorderLayout.CENTER);
		
		// Construction du panel mapping string
	
		panelMappingString = new JPanel(new BorderLayout());
			
		LMappingString = new JLabel("Mapping field :", JLabel.TRAILING);
		panelMappingString.add(LMappingString, BorderLayout.NORTH);
				
		CBMappingString = new JList<MappingString>(getDAcontroller().getDAModele().getTabMappingString());
		CBMappingString.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CBMappingString.setLayoutOrientation(JList.VERTICAL);
		CBMappingString.setVisibleRowCount(-1);
		LMappingString.setLabelFor(CBMappingString);
		CBMappingString.addListSelectionListener(new EcouteurListe(this));
		JScrollPane listScroller2 = new JScrollPane(CBMappingString);
		listScroller2.setPreferredSize(new Dimension(150, 500));
		JPanel containerMapList = new JPanel(new FlowLayout());
		containerMapList.add(LMappingString);
		panelMappingString.add(containerMapList, BorderLayout.NORTH);
		panelMappingString.add(listScroller2, BorderLayout.CENTER);
		
		
		// Construction du panel mapping INT
		
		panelMappingINT = new JPanel(new BorderLayout());
	
		LMappingINT = new JLabel("Mapping values :", JLabel.TRAILING);
		panelMappingINT.add(LMappingINT, BorderLayout.NORTH);
				
		CBMappingINT = new JList<MappingINT>(getDAcontroller().getDAModele().getTabMappingINT());
		CBMappingINT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CBMappingINT.setLayoutOrientation(JList.VERTICAL);
		CBMappingINT.setVisibleRowCount(-1);
		LMappingINT.setLabelFor(CBMappingINT);
		CBMappingINT.addListSelectionListener(new EcouteurListe(this));
		JScrollPane listScroller3 = new JScrollPane(CBMappingINT);
		listScroller3.setPreferredSize(new Dimension(150, 500));
		JPanel containerMapList2 = new JPanel(new FlowLayout());
		containerMapList2.add(LMappingINT);
		panelMappingINT.add(containerMapList2, BorderLayout.NORTH);
		panelMappingINT.add(listScroller3, BorderLayout.CENTER);
		
		JPanel minMaxPanel = new JPanel(new BorderLayout());
		JPanel minP = new JPanel(new FlowLayout());
		JPanel maxP = new JPanel(new FlowLayout());
		JPanel filterB = new JPanel(new FlowLayout());
				
		JLabel minLabel = new JLabel("Minimum");
		minF = new JTextField(15);
		minP.add(minLabel);
		minP.add(minF);
		JLabel maxLabel = new JLabel("Maxmum");
		maxF = new JTextField(15);
		maxP.add(maxLabel);
		maxP.add(maxF);
		triFourchette = new JButton("Filter");
		triFourchette.addActionListener(new EcouteurBouton(this));
		filterB.add(triFourchette);
		
		minMaxPanel.add(minP, BorderLayout.NORTH);
		minMaxPanel.add(maxP, BorderLayout.CENTER);
		minMaxPanel.add(filterB, BorderLayout.SOUTH);
		
		panelMappingINT.add(minMaxPanel, BorderLayout.SOUTH);
		
		// Construction panel central
		
		panelMapping = new JPanel(new SpringLayout());
		
		lValue = new JLabel("Field :", JLabel.TRAILING);
		panelMapping.add(lValue);
		value = new JLabel("");
		lValue.setLabelFor(value);
		panelMapping.add(value);
		
		lStorage = new JLabel("Storage :", JLabel.TRAILING);
		panelMapping.add(lStorage);
		storage = new JLabel("");
		lStorage.setLabelFor(storage);
		panelMapping.add(storage);
		
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
		
		lClasseValue = new JLabel("Class :", JLabel.TRAILING);
		panelMapping.add(lClasseValue);
		classeValue = new JLabel("");
		lClasseValue.setLabelFor(classeValue);
		panelMapping.add(classeValue);
		
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
		
		
		lChoixRadio = new JLabel("Keep or remove selected values :", JLabel.TRAILING);
		panelMapping.add(lChoixRadio);
		JPanel pRadio = new JPanel(new BorderLayout());
		keep = new JRadioButton("Keep");
		remove = new JRadioButton("Remove");
		choixRadio = new ButtonGroup();
		choixRadio.add(keep);
		choixRadio.add(remove);
		keep.addActionListener(new EcouteurBouton(this));
		remove.addActionListener(new EcouteurBouton(this));
		remove.isSelected();
		pRadio.add(keep, BorderLayout.CENTER);
		pRadio.add(remove, BorderLayout.WEST);
		panelMapping.add(pRadio);
				
		// Valeurs fréquentes
		
		lvaleursfrequentes = new JLabel("Values : ");

		panelMapping.add(lvaleursfrequentes);
		
		String[] dataJListVF = Arrays.copyOf(this.getDAcontroller().getDAModele().getTabColonne()[this.getListeColonne().getSelectedIndex()].getValeursListe().toArray(), this.getDAcontroller().getDAModele().getTabColonne()[this.getListeColonne().getSelectedIndex()].getValeursListe().size(), String[].class);
		
		listVF = new JList<String>(dataJListVF);
		listVF.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listVF.setLayoutOrientation(JList.VERTICAL);
		listVF.setVisibleRowCount(-1);
		listVF.addListSelectionListener(new EcouteurListe(this));
		
		JScrollPane ScrollerVF = new JScrollPane(listVF);

		panelMapping.add(ScrollerVF);
		
		
		
		lSelectValue = new JLabel("Select this field in the repporting :", JLabel.TRAILING);
		panelMapping.add(lSelectValue);
		selectValue = new JCheckBox();
		lSelectValue.setLabelFor(selectValue);
		selectValue.addActionListener(new EcouteurCB(this));
		panelMapping.add(selectValue);
		

		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(panelMapping,
		                                11, 2, //rows, cols
		                                10, 10,        //initX, initY
		                                10, 10);       //xPad, yPad
		
		
		JPanel panelContainer = new JPanel(new BorderLayout());
		panelContainer.add(panelMapping, BorderLayout.CENTER);
		//panelContainer.add(valeursfrequentes, BorderLayout.SOUTH);
		
		// Construction du JPanel de boutons
		
		pRepporting = new JPanel();
		
		back = new JButton("< Back");
		back.addActionListener(new EcouteurBouton(this));
		pRepporting.add(back);
		
		repporting = new JButton("Edit Repporting");
		selectAll = new JButton("Select all");
		deselectAll = new JButton("Deselect all");		
		
		pRepporting.setLayout(new FlowLayout());
		selectAll.addActionListener(new EcouteurBouton(this));
		pRepporting.add(selectAll);
		
		deselectAll.addActionListener(new EcouteurBouton(this));
		pRepporting.add(deselectAll);
		
		repporting.addActionListener(new EcouteurBouton(this));
		repporting.setEnabled(false);
		pRepporting.add(repporting);
		
		LKeepTable = new JLabel("Keep table after use :");
		pRepporting.add(LKeepTable);
		
		CBKeepTable = new JCheckBox();
		pRepporting.add(CBKeepTable);
		
		
		
		// Population de la fenetre
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(pInfo, BorderLayout.NORTH);
		this.add(getPanelColonnes(), BorderLayout.WEST);
		//this.add(panelMappingString, BorderLayout.EAST);
		//panelMappingString.setVisible(false);
		//this.add(panelMappingINT, BorderLayout.EAST);
		//panelMappingINT.setVisible(false);
		this.add(panelContainer, BorderLayout.CENTER);
		panelMapping.setVisible(false);
		this.add(pRepporting, BorderLayout.SOUTH);
		
	}
	
}
