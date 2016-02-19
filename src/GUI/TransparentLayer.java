package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class TransparentLayer extends JPanel {
	MouseController m;
	Vector<Dimension> coordinates;
	
	public TransparentLayer() {
		this.addMouseListener(m);
		setPreferredSize(new Dimension(400, 300));
		m = new MouseController();
		coordinates = new Vector<Dimension>();
		setOpaque(false);
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		Iterator<Dimension> it = coordinates.iterator();
		while (it.hasNext()) {
			Dimension d = it.next();
			g.setColor(Color.RED);
			g.drawOval((int)d.getWidth(), (int)d.getHeight(), 5, 5);
		}
	}
	
	public void resetDots() {
		coordinates = new Vector<Dimension>();
	}

	/**
	 * each time the the mouse is clicked
	 * a dot will be saved
	 *
	 * (seems should be two)
	 */
	public class MouseController extends MouseInputAdapter {
		
		public void mousePressed(MouseEvent e) {
			Tester t = new Tester();
			int xMax = (int)getSize().getWidth();
			int yMax = (int)getSize().getHeight();
			int x = e.getX();
			int y = e.getY();
			if ((x <= xMax) & (y <= yMax)) {
				coordinates.addElement(new Dimension(x,y));
				repaint();
			}
		}
	}
}
