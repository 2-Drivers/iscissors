/**
 * \file MainDialog.java
 * \brief this is a Java class that creates the main dialog of this program
 * 
 * It is designed in Singeleton Pattern -- well I am not sure yet
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.PaintController;


/**
 * this is the GUI interface for the main dialog of the program
 * in the dialog there will be a menu list
 * as well as a drawing pad inheriting from JPanel
 * which is aimed to be a canvas
 * since there is shortage of functions in Canvas class
 * 
 * @author JX
 *
 */
public class MainDialog extends JFrame {
	private JFrame frame;
	private JMenuBar menuBar;
	private JLabel label;
	
	private DrawingPad drawingPad;
	private PaintController brian;
	private JScrollPane jsp;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//we don't need a constructor
	public MainDialog() {
		this.setSize(400,300);
		brian = new PaintController();
		//Image bgimage = new ImageIcon("/home/JX/code/comp5421/iscissors/test.jpg").getImage();
		drawingPad = new DrawingPad();
		jsp = new JScrollPane(drawingPad);
		jsp.setPreferredSize(drawingPad.getSize());
		jsp.setVisible(true);
		getContentPane().add(jsp);
		createAllButtons();
	}
	
	public void setDrawingPad() {
		jsp.setOpaque(true);
		jsp.getViewport().setOpaque(true);
	}
	
	/**
	 * it creates the buttons on the menu list
	 * 
	 * but many of them is yet to be finished
	 */
	private void createAllButtons() {
		// initialize the components
		frame = new JFrame();
		menuBar = new JMenuBar();
		this.setTitle("IScissors");
		this.setJMenuBar(menuBar);
		
		JMenu menu1, menu2, menu3;
		menu1 = new JMenu("File");
		menu2 = new JMenu("Select mode");
		menu3 = new JMenu("help");
		
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		
		JMenuItem item1_1;
		item1_1 = new JMenuItem("Test Item");
		item1_1.addActionListener(new ActionListener(){

			@Override
			/**
			 * once a picture is selected the interface is handled to be updated
			 * 
			 * to do: the cases when no picture is selected, i.e. return null 
			 */
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//brian.test();
				Image i = null;
				try {
					i = brian.readFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				drawingPad.setImage(i);
				int height = i.getHeight(null);
				int width = i.getWidth(null);
				jsp.setPreferredSize(new Dimension(width, height));
				drawingPad.setPreferredSize(new Dimension(width, height));
				jsp.resize(new Dimension(width+10, height+10));
				setSize(new Dimension(width+20, height+80));
				jsp.validate();
				repaint();
			}
		});
		menu1.add(item1_1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
