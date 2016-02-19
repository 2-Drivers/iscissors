package GUI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
 * this is the drawing pad where we can actually
 * get the signal and do things on the pictures
 */
public class DrawingPad extends JPanel {
	//JPanel realPanel;
	boolean imageChanged, imageSet;
	Image i;
	JLabel imageLabel;
	MouseController m;
	Vector<Dimension> coordinates;
	
	public DrawingPad() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(400,300));
		imageChanged = false;
		imageSet = false;
		i = null;
		m = new MouseController();
		this.addMouseListener(m);
		imageLabel = new JLabel();
		coordinates = new Vector<Dimension>();
		//setVisible(true);
	}

	/**
	 * This function will set the background image for the current canvas
	 * note that once set
	 * all previous set dots will be deleted
	 * 
	 * @param i2: the input image 
	 */
	public void setImage(Image i2) {
		i = i2;
		imageSet = true;
		imageChanged = true;
		repaint();
		int height = i2.getHeight(null);
		int width = i2.getWidth(null);
		setPreferredSize(new Dimension(width, height));
		coordinates = new Vector<Dimension>();
		setOpaque(true);
		validate();
	}
	
	/**
	 * overload the default method
	 * so that each time the picture and vector dots are repainted
	 * looking for better methods
	 * i.e. more effective one
	 */
	public void paintComponent(Graphics g) {
		System.out.println("123");
		g.drawImage(i, 0, 0, null);
		imageChanged = false;
		
		Iterator<Dimension> it = coordinates.iterator();
		while (it.hasNext()) {
			Dimension d = it.next();
			g.setColor(Color.RED);
			g.drawOval((int)d.getWidth(), (int)d.getHeight(), 5, 5);
		}
	}
	
	/**
	 * each time the the mouse is clicked
	 * a dot will be saved
	 *
	 * (seems should be two)
	 */
	public class MouseController extends MouseInputAdapter {
		public void mousePressed(MouseEvent e) {
			if (!imageSet) {;} else {
				int xMax = i.getWidth(null);
				int yMax = i.getHeight(null);
				int x = e.getX();
				int y = e.getY();
				if ((x <= xMax) && (y <= yMax)) {
					coordinates.addElement(new Dimension(x,y));
					repaint(new Rectangle(x-3,y-3,x+3,y+3));
				}
			}
		}
	}
}
