package Algorithm;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class ImageStructure {
	ImageNodeStructure[][] pixels; //see its definition
	
	/**
	 * However you make the structure
	 * there should be a constructor with an input image
	 * @param i is the image I loaded
	 */
	public ImageStructure(BufferedImage i) {
		
	}
	
	/**
	 * 
	 * @param StartX
	 * @param StartY
	 * @param endX
	 * @param endY
	 * @return in the form of {startnode, next, next ... endnode}
	 */
	int[] getShortestPath(int StartX, int StartY, int endX, int endY) {
		return null;
	}
	
	/**
	 * this is only implemented when necessary
	 * @return
	 */
	int getPathLength() {
		return 0;
	}
}
