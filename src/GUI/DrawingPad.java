package GUI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputAdapter;

import Controller.PaintController;

/**
 * 
 * @author JX
 **this is the drawing pad where we can actually
 * get the signal and do things on the pictures
 */
public class DrawingPad extends JPanel {
	//JPanel realPanel;
	boolean imageChanged = false;
	Image i;
	JLabel imageLabel;
	MouseController m;
	Vector<Dimension> coordinates;
	
	public DrawingPad() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(400,300));
		i = null;
		m = new MouseController();
		this.addMouseListener(m);
		imageLabel = new JLabel();
		coordinates = new Vector<Dimension>();
		//setVisible(true);
	}

	public void setImage(Image i2) {
		i = i2;
		imageChanged = true;
		repaint();
		int height = i2.getHeight(null);
		int width = i2.getWidth(null);
		setPreferredSize(new Dimension(width, height));
		ImageIcon ic = new ImageIcon(i);
		imageLabel = new JLabel(ic);
		imageLabel.setVerticalAlignment(JLabel.TOP);
		imageLabel.setHorizontalAlignment(JLabel.LEFT);
		coordinates = new Vector<Dimension>();
		
		setOpaque(true);
		validate();
	}
	
	public void paintComponent(Graphics g) {	
		g.drawImage(i, 0, 0, null);
		
		Iterator<Dimension> it = coordinates.iterator();
		while (it.hasNext()) {
			Dimension d = it.next();
			g.drawOval((int)d.getWidth(), (int)d.getHeight(), 5, 5);
		}
	}
	
	public class MouseController extends MouseInputAdapter {
		boolean oddPressTime = false;
		
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			//oddPressTime = !oddPressTime;
			coordinates.addElement(new Dimension(x,y));
			repaint();
		}
	}
}
