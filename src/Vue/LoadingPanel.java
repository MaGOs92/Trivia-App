package Vue;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LoadingPanel extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel imagePanel = new JPanel();
    JLabel imageLabel = new JLabel();
    JPanel headerPanel = new JPanel();
    JLabel headerLabel = new JLabel();
    Box boite;
    
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

	public LoadingPanel() {
        	
     		setTitle("In process");
    		setIconImage(Toolkit.getDefaultToolkit().getImage("Img\\icone.png")); 
    		
     		boite = Box.createVerticalBox();
    		
     		this.setLayout(new BorderLayout());
           
            // add the header label
            headerLabel.setText("Loading ...");
            headerPanel.add(headerLabel);
            boite.add(headerPanel, BorderLayout.NORTH);
            
            // add the image label
            ImageIcon ii = new ImageIcon("Img\\loader.gif");
            imageLabel.setIcon(ii);
            imagePanel.add(imageLabel);
            boite.add(imagePanel, BorderLayout.CENTER);
            
            this.add(boite);
            
    		pack();
    		setLocationRelativeTo(null);
    		setResizable(false);
    		setVisible(false);

    }
	
}
