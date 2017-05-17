package Minehunter;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

class JImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon img;
	public JImagePanel(ImageIcon img) {
		this.img = img;
	}
	public void paintComponent(Graphics g){
		g.drawImage(img.getImage(), 0, 0, null);
	}
}