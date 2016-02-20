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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import Algorithm.Coordinate;
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
	KeyboardController k;
	Vector<Coordinate> coordinates;
	
	public DrawingPad() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(400,300));
		imageChanged = false;
		imageSet = false;
		i = null;
		m = new MouseController();
		this.addMouseListener(m);
		k = new KeyboardController();
		this.addKeyListener(k);
		this.setFocusable(true);
		requestFocusInWindow();
		imageLabel = new JLabel();
		coordinates = new Vector<Coordinate>();
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
		coordinates = new Vector<Coordinate>();
		setOpaque(true);
		validate();
	}
	
	public Image getImage() {
		return i;
	}
	
	/**
	 * overload the default method
	 * so that each time the picture and vector dots are repainted
	 * looking for better methods
	 * i.e. more effective one
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
		imageChanged = false;
		
		Iterator<Coordinate> it = coordinates.iterator();
		while (it.hasNext()) {
			Coordinate c = it.next();
			g.setColor(Color.RED);
			g.drawOval((int)c.x(), (int)c.y(), 5, 5);
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
					coordinates.addElement(new Coordinate(x,y));
					repaint(new Rectangle(x-3,y-3,x+3,y+3));
				}
			}
		}
	}
	
	public class KeyboardController extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println(arg0.getKeyCode());
			if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				//System.out.println("backspace");
				if (coordinates.size() != 0) {
					coordinates.remove(coordinates.size() - 1);
					repaint(); //<!which one>
				}
			} else if (true) {
				;
			}
		}
	}
	
	private void setKeys() {
	}
}
