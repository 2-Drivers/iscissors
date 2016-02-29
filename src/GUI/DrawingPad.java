package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
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
	MousemoveListener l;
	KeyboardController k;
	Vector<Coordinate> coordinates;
	
	Vector<Coordinate> path;
	
	int expandTime;
	boolean enabled;
	private PaintController brian;
	
	boolean ctrlKey = false;
	boolean plusKey = false;
	boolean minusKey = false;
	boolean enterKey = false;
	boolean backspaceKey = false;
	
	public DrawingPad(PaintController pc) {
		brian = pc;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(400,300));
		imageChanged = false;
		imageSet = false;
		i = null;
		m = new MouseController();
		l = new MousemoveListener();
		this.addMouseListener(m);
		this.addMouseMotionListener(l);
		k = new KeyboardController();
		this.addKeyListener(k);
		this.setFocusable(true);
		requestFocusInWindow();
		imageLabel = new JLabel();
		
		path = new Vector<Coordinate>();
		coordinates = new Vector<Coordinate>();
		//setVisible(true);
		
		expandTime = 0;
		enabled = false;
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
			g.drawOval((int)c.x(), (int)c.y(), 0, 0);
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
			if (!imageSet) {
				;
			} else {
				if (!enabled) {
					if (ctrlKey) {
						enabled = true;
						System.out.println(e.getX());
						System.out.println(e.getY());
					}
				}
			
				if (enabled) {
					int xMax = i.getWidth(null);
					int yMax = i.getHeight(null);
					int x = e.getX();
					int y = e.getY();
					if ((coordinates == null) & (ctrlKey)) {
						coordinates = new Vector<Coordinate>();
					}
					if ((x <= xMax) && (y <= yMax)) {
						coordinates.addElement(new Coordinate(x,y));
						repaint(new Rectangle(x,y,x,y));
					}
					Iterator<Coordinate> it = path.iterator();
					
					System.out.println(path.size());
					
					while (it.hasNext()) {
						Coordinate c = it.next();
						coordinates.add(c);
					}
					System.out.println(coordinates.size());
					repaint();
				}
			}
		}
		/*
		public void mouseDragged(MouseEvent e) {
			int currentX = e.getX();
			int currentY = e.getY();
			int lastX = coordinates.lastElement().x();
			int lastY = coordinates.lastElement().y();
			
			System.out.println(currentX);
			//new Coordinate(currentX, currentY);
			
			Vector<Coordinate> path = brian.cc.getShortestPath(new Coordinate(lastX, lastY), new Coordinate(currentX, currentY));
		}
		*/
	}
	
	public class MousemoveListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			//Point p = MouseInfo.getPointerInfo().getLocation();
			int currentX = e.getX();
			int currentY = e.getY();
			if (coordinates.size() != 0) {
				int lastX = coordinates.lastElement().x();
				int lastY = coordinates.lastElement().y();
				path = brian.cc.getShortestPath(new Coordinate(lastX, lastY), new Coordinate(currentX, currentY));
			}
		}
		
	}
	
	public class KeyboardController extends KeyAdapter {

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			int i = arg0.getKeyCode();
			switch(i) {
			case KeyEvent.VK_CONTROL: ctrlKey = true; break;
			case KeyEvent.VK_ADD: plusKey = true; break;
			case KeyEvent.VK_MINUS: minusKey = true; break;
			case KeyEvent.VK_ENTER: enterKey = true; break;
			case KeyEvent.VK_BACK_SPACE: backspaceKey = true; break;
			}
			if (backspaceKey) {
				//System.out.println("backspace");
				if (coordinates.size() != 0) {
					coordinates.remove(coordinates.size() - 1);
					repaint(); //<!which one>
				}
				
				if(coordinates.size() == 0) {
					enabled = false;
				}
			} else if (ctrlKey) {
				if (plusKey) {
					expandTime += 1;
					plusKey = false;
				} else if (minusKey) {
					expandTime -= 1;
					plusKey = false;
				} else if (enterKey) {
					enabled = false;
				}
				//implement other methods;
			} else if (enterKey) {
				; //?
			}
		}
		
		public void keyReleased(KeyEvent arg0) {
			int i = arg0.getKeyCode();
			switch (i) {
			case KeyEvent.VK_CONTROL: ctrlKey = false; break;
			case KeyEvent.VK_ADD: plusKey = false; break;
			case KeyEvent.VK_MINUS: minusKey = false; break;
			case KeyEvent.VK_ENTER: enterKey = false; break;
			case KeyEvent.VK_BACK_SPACE: backspaceKey = false; break;
			}
		}
	}
}
