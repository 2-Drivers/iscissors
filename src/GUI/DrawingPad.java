package GUI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.PaintController;

/**
 * 
 * @author JX
 **this is the drawing pad where we can actually
 * get the signal and do things on the pictures
 */
public class DrawingPad extends JPanel {
	//JPanel realPanel;
	Image i;
	
	public DrawingPad() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(400,300));
		i = null;
		//setVisible(true);
	}

	public void setImage(Image i2) {
		i = i2;
		validate();
		int height = i2.getHeight(null);
		int width = i2.getWidth(null);
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
	}
}
