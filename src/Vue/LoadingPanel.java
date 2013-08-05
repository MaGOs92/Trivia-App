package Vue;

import java.awt.*;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import Controleur.CoImpControleur;

public class LoadingPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel imagePanel = new JPanel(new FlowLayout());
    JLabel imageLabel = new JLabel();
    JPanel headerPanel = new JPanel(new FlowLayout());
    JLabel headerLabel = new JLabel();
    CoImpControleur controller;
    
    
    
    
	public CoImpControleur getController() {
		return controller;
	}

	public void setController(CoImpControleur controller) {
		this.controller = controller;
	}

	public JPanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(JPanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JPanel getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(JPanel headerPanel) {
		this.headerPanel = headerPanel;
	}

	public JLabel getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(JLabel headerLabel) {
		this.headerLabel = headerLabel;
	}

	public LoadingPanel(CoImpControleur controller) {
        	
        	this.setController(controller);
        	
        	this.setLayout(new BorderLayout());
           
            // add the header label
            headerLabel.setText("Loading ...");
            headerPanel.add(headerLabel);
            this.add(headerPanel, java.awt.BorderLayout.NORTH);
            
            // add the image label
            ImageIcon ii = new ImageIcon("Img\\loader.gif");
            imageLabel.setIcon(ii);
            imagePanel.add(imageLabel);
            this.add(imagePanel, java.awt.BorderLayout.CENTER);

    }

    public static void main(String[] args) {
    	     
		JFrame fenetre = new JFrame ("Trivia DataDiscovery");
		fenetre.setSize(500, 230);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Trivia DataDiscovery");
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran
	
		fenetre.setVisible(true);
		


    }

}
