package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.ImageStructure;
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
	
	ImageStructure ist;
	private ComputeController cc;
	
	public Tester test() {
		return new Tester();
	}
	
	public Tester test(String s) {
		return new Tester(s);
	}
	
	public void setImageStructure(BufferedImage i) {
		cc.setImageStructure(i);
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
	
	/**
	 * 
	 * @return the image including the contours
	 * @throws IOException
	 */
	private void writeImage(BufferedImage i) throws IOException {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter f = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(f);
		int returnVal = chooser.showSaveDialog(null);
		File fileToSave;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileToSave = chooser.getSelectedFile();
			try {
				ImageIO.write(i, "jpg", fileToSave);
				System.out.println(fileToSave.getName());
			} catch (IOException ex) {
				System.out.println("zzzzz");
			}
		}
	}
	
	public void saveFile(JPanel jp) {
		int w = jp.getWidth();
		int h = jp.getHeight();
		jp.repaint();
		BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics g2 = bi.createGraphics();
		jp.paint(g2);
		try {
			writeImage(bi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawImage(Image i) {
		Graphics2D g = (Graphics2D) (i.getGraphics());
	}
}
