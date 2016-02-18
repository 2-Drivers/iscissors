package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.MainDialog;
import GUI.Tester;


/**
 * 
 * @author JX
 **This is a controller in the window
 * It is a member of window
 * can be initialized only once
 */
public class PaintController {
	private static PaintController singeleton = null;
	
	//private MainDialog mainDialog;
	//private MouseListener mListener;
	//private KeyboardListener kListener;

	public PaintController() 
	{
		;
	}
	
	public Tester test() {
		return new Tester();
	}
	
	public Tester test(String s) {
		return new Tester(s);
	}
	
	public BufferedImage readFile() throws IOException {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter f = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(f);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f2 = chooser.getSelectedFile();
			BufferedImage returnValue = ImageIO.read(f2);
			return returnValue;
		} else return null;
	}
	
	public void drawImage(Image i) {
		Graphics2D g = (Graphics2D) (i.getGraphics());
	}
}
