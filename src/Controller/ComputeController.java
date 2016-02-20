package Controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Algorithm.Coordinate;
import Algorithm.ImageStructure;

public class ComputeController {

	ImageStructure ist;
	
	public void setImageStructure(BufferedImage i) {
		ist = new ImageStructure(i);// TODO Auto-generated method stub
	}
	//no constructor
	
	/**
	 * this is a must for the program
	 * use the algorithm to compute shortest path
	 * @param c1
	 * @param c2
	 * @return
	 */
	public Coordinate[] getShortestPath(Coordinate c1, Coordinate c2) {
		return null;
	}
	
	/**
	 * I haven't decided what to do with this
	 * it may or may not be used
	 * @return
	 */
	public Vector<Vector<Coordinate>> getContour() {
		return null;
	}
}
