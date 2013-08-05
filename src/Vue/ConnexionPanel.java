package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mysql.jdbc.Connection;

import javax.swing.*;

import Controleur.CoImpControleur;
import Modele.ConnexionModele;

public class ConnexionPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	CoImpControleur controleur = null;
	
	JLabel LServer;
	JTextField TServer;
	JLabel LPort;
	JTextField TPort;
	JLabel LDB;
	JTextField TDB;
	JLabel LUser;
	JTextField TUser;
	JLabel LPassword;
	JPasswordField TPassword;
	
	ConnexionModele modele = null;
	
	Connection co = null;
	
	JButton connexion;
	
	JButton next;
	
	JLabel info;
	
	

	public CoImpControleur getControleur() {
		return controleur;
	}

	public void setControleur(CoImpControleur controleur) {
		this.controleur = controleur;
	}

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public Connection getCo() {
		return co;
	}

	public void setCo(Connection co) {
		this.co = co;
	}

	public ConnexionModele getModele() {
		return modele;
	}

	public void setModele(ConnexionModele modele) {
		this.modele = modele;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public JLabel getLServer() {
		return LServer;
	}

	public void setLServer(JLabel lServer) {
		LServer = lServer;
	}

	public JTextField getTServer() {
		return TServer;
	}

	public void setTServer(JTextField tServer) {
		TServer = tServer;
	}

	public JLabel getLPort() {
		return LPort;
	}

	public void setLPort(JLabel lPort) {
		LPort = lPort;
	}

	public JTextField getTPort() {
		return TPort;
	}

	public void setTPort(JTextField tPort) {
		TPort = tPort;
	}

	public JLabel getLDB() {
		return LDB;
	}

	public void setLDB(JLabel lDB) {
		LDB = lDB;
	}

	public JTextField getTDB() {
		return TDB;
	}

	public void setTDB(JTextField tDB) {
		TDB = tDB;
	}

	public JLabel getLUser() {
		return LUser;
	}

	public void setLUser(JLabel lUser) {
		LUser = lUser;
	}

	public JTextField getTUser() {
		return TUser;
	}

	public void setTUser(JTextField tUser) {
		TUser = tUser;
	}

	public JLabel getLPassword() {
		return LPassword;
	}

	public void setLPassword(JLabel lPassword) {
		LPassword = lPassword;
	}

	public JPasswordField getTPassword() {
		return TPassword;
	}

	public void setTPassword(JPasswordField tPassword) {
		TPassword = tPassword;
	}

	public JButton getConnexion() {
		return connexion;
	}

	public void setConnexion(JButton connexion) {
		this.connexion = connexion;
	}

	public ConnexionPanel(CoImpControleur controleur){
		
		this.controleur = controleur;
		
		setLayout(new BorderLayout());
		
		String[] labels = {"Server : ", "Port : ", "Database : ", "User : ", "Password : "};
		int numPairs = labels.length;

		//Create and populate the panel.
		JPanel champs = new JPanel(new SpringLayout());
		// Champ server
		LServer = new JLabel(labels[0], JLabel.TRAILING);
		champs.add(LServer);
		TServer = new JTextField(20);
		LServer.setLabelFor(TServer);
		champs.add(TServer);
		TServer.setText("localhost");
		
		// Champ port
		LPort = new JLabel(labels[1], JLabel.TRAILING);
		champs.add(LPort);
		TPort = new JTextField(20);
		LPort.setLabelFor(TPort);
		champs.add(TPort);
		TPort.setText("3306");
		
		// Champ database
		LDB = new JLabel(labels[2], JLabel.TRAILING);
		champs.add(LDB);
		TDB = new JTextField(20);
		LDB.setLabelFor(TDB);
		champs.add(TDB);
		TDB.setText("dbtriviacsv");
		
		// Champ user
		LUser = new JLabel(labels[3], JLabel.TRAILING);
		champs.add(LUser);
		TUser = new JTextField(20);
		LUser.setLabelFor(TUser);
		champs.add(TUser);
		TUser.setText("root");
		
		// Champs password
		LPassword = new JLabel(labels[4], JLabel.TRAILING);
		champs.add(LPassword);
		TPassword = new JPasswordField(20);
		LPassword.setLabelFor(TPassword);
		champs.add(TPassword);
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(champs,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		
		//champs.setBorder(BorderFactory.createLineBorder(Color.black)); Pour ajouter une bordure
		
		// Ajout des champs
		
		JPanel inf = new JPanel();
		inf.setLayout(new FlowLayout());
		
		this.setInfo(new JLabel("Please enter your login information."));
		inf.add(getInfo());
		
		this.add(inf, BorderLayout.NORTH);
		
		this.add(champs, BorderLayout.CENTER);
		
		
		// Panel de connexion
		JPanel panelConnexion = new JPanel();

		this.setConnexion(new JButton("Test connection"));
		this.setNext(new JButton("Next >"));
		this.getNext().setEnabled(false);
		panelConnexion.setLayout(new FlowLayout());
		panelConnexion.add(getConnexion());
		panelConnexion.add(getNext());
		
		// Créer un écouteur		
		
		getConnexion().addActionListener(this);
		
		getNext().addActionListener(this);
		
		this.add(panelConnexion, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.getConnexion() ){

        	this.setModele(new ConnexionModele(this.getTServer().getText(), this.getTPort().getText(), this.getTDB().getText(), this.getTUser().getText(), new String (this.getTPassword().getPassword())));

        	if (getModele().openConnection() != null){
        		this.setCo(getModele().openConnection());
        		this.getInfo().setForeground(Color.blue);
        		this.getInfo().setText("Connected to mySQL Server.");
        		this.getNext().setEnabled(true);
        	}
        	else{
        		getInfo().setForeground(Color.red);
        		this.getInfo().setText("Connection failed. Please check your login information.");
        	}
        	
		}
		
		if (e.getSource() == this.getNext() ){

			this.getControleur().showImp();

		}
		 
	}
	
	public static void main (String[] args){
		
		CoImpControleur CoController = new CoImpControleur();
		
		
	}
}

