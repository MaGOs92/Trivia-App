package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;

import Controleur.CoImpControleur;

public class ImportationPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JButton parcourir;
	JLabel info;
	JButton dataAudit;
	JLabel fichier;
	String pathFichier;
	FileFilter filtre;
	CoImpControleur controller;


	public CoImpControleur getController() {
		return controller;
	}

	public void setController(CoImpControleur controller) {
		this.controller = controller;
	}

	public FileFilter getFiltre() {
		return filtre;
	}

	public void setFiltre(FileFilter filtre) {
		this.filtre = filtre;
	}

	public JButton getDataAudit() {
		return dataAudit;
	}

	public void setDataAudit(JButton dataAudit) {
		this.dataAudit = dataAudit;
	}

	public JLabel getFichier() {
		return fichier;
	}

	public void setFichier(JLabel fichier) {
		this.fichier = fichier;
	}

	public String getPathFichier() {
		return pathFichier;
	}

	public void setPathFichier(String pathFichier) {
		this.pathFichier = pathFichier;
	}

	public JButton getParcourir() {
		return parcourir;
	}

	public void setParcourir(JButton parcourir) {
		this.parcourir = parcourir;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public ImportationPanel(CoImpControleur controller){		
		
		this.setController(controller);
		
		this.setLayout(new BorderLayout());
		
		JPanel importer = new JPanel();
		importer.setLayout(new FlowLayout());
		JPanel dataAudit = new JPanel();
		dataAudit.setLayout(new FlowLayout());
		
		JPanel milieu = new JPanel();
		milieu.setLayout(new BorderLayout());
		
		JPanel fic = new JPanel();
		fic.setLayout(new FlowLayout());
		
		JPanel mes = new JPanel();
		mes.setLayout(new BorderLayout());

		
		JPanel loa = new JPanel();
		loa.setLayout(new BorderLayout());		

		
		this.setInfo(new JLabel("Select the CSV file that you want to audit."));
		importer.add(getInfo());
		
		this.setParcourir(new JButton("Browse..."));
		this.getParcourir().addActionListener(this);
		importer.add(getParcourir());
		
		this.setDataAudit(new JButton("Start Data Audit"));
		this.getDataAudit().addActionListener(this);
		this.getDataAudit().setEnabled(false);
		dataAudit.add(getDataAudit());
		
		this.setFichier(new JLabel("Selected File : none"));
		fic.add(getFichier());
		
		this.add(importer, BorderLayout.NORTH);
		this.add(dataAudit, BorderLayout.SOUTH);
		this.add(fic, BorderLayout.CENTER);
	}
	

	
	 public void actionPerformed(ActionEvent ae) {
		 
		 if (ae.getSource() == this.getParcourir()){
			 
			 FileSystemView vueSysteme = FileSystemView.getFileSystemView(); 
			 
			 ExampleFileFilter filter = new ExampleFileFilter();
			 
			 filter.addExtension("csv");
			 filter.setDescription("CSV files");
			 
			 File home = vueSysteme.getHomeDirectory(); 
			 
	        JFileChooser fileChooser = new JFileChooser(home);
	        
	        fileChooser.setFileFilter(filter);
	        fileChooser.setAcceptAllFileFilterUsed(false);
	        
	        int returnValue = fileChooser.showOpenDialog(null);
	        
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          this.setPathFichier(selectedFile.getAbsolutePath());
	          this.getFichier().setText("Selected file : " + selectedFile.getAbsolutePath());
	          this.getDataAudit().setEnabled(true);
	          
	        }
		 }
		 
		 else if (ae.getSource() == this.getDataAudit()){
			
			try{
			this.getController().lancerDataAudit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		 }
		 
	 }
	 
	public static void main(String[] args){
		
		JFrame fenetre = new JFrame ("DataAudit");
		fenetre.setSize(500, 230);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("DataAudit");
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		ImportationPanel IP = new ImportationPanel(new CoImpControleur());
		
		
		fenetre.getContentPane().add(IP);
		
		fenetre.pack();
		fenetre.setVisible(true);
	}
	
}