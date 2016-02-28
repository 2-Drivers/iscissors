package Algorithm;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

public class ImageStructure {
	Vector<ImageNodeStructure> pixels; //see its definition
	int height;
	int width;
	
	public ImageStructure() {
		;
	}
	
	/**
	 * However you make the structure
	 * there should be a constructor with an input image
	 * @param i is the image I loaded
	 */
	public ImageStructure(BufferedImage i) {
		height = i.getHeight();
		width = i.getWidth();
		
		pixels = new Vector<ImageNodeStructure>();
		initializeAllThroughImage(i);
	}
	
	private void initializeAllThroughImage(BufferedImage i) {
		for (int j = 0; j < height; ++j) {
			for (int k = 0; k < width; ++k) {
				Color pixelValueARGB = new Color(i.getRGB(k,j));
				pixels.add(new ImageNodeStructure(pixelValueARGB));
			}
		}
		computeEdges();
	}
	
	private void computeEdges() {
		Iterator<ImageNodeStructure> it = pixels.iterator();
		int index = 0;
		while (it.hasNext()) {
			index += 1;
			ImageNodeStructure temp = it.next();
			if (index %  width == 0) {
				temp.edges[0] = Integer.MAX_VALUE;
				temp.edges[1] = Integer.MAX_VALUE;
				temp.edges[7] = Integer.MAX_VALUE;
			} 
			if ((index - 1) % width == 0){
				temp.edges[3] = Integer.MAX_VALUE;
				temp.edges[4] = Integer.MAX_VALUE;
				temp.edges[5] = Integer.MAX_VALUE;
				
			}
			if (index > width * height - width) {
				temp.edges[5] = Integer.MAX_VALUE;
				temp.edges[6] = Integer.MAX_VALUE;
				temp.edges[7] = Integer.MAX_VALUE;
			}
			if (index <= width) {
				temp.edges[1] = Integer.MAX_VALUE;
				temp.edges[2] = Integer.MAX_VALUE;
				temp.edges[3] = Integer.MAX_VALUE;
			}
			
			if (temp.edges[0] != Integer.MAX_VALUE) {
				if (index <= width) {
					temp.edges[0] = edgeCost(pixels.get(index - 1), pixels.get(index - 1 + width), 1) / 2
							+ edgeCost(pixels.get(index), pixels.get(index + width), 1) / 2;
				} else if (index > width * (height - 1)) {
					temp.edges[0] = edgeCost(pixels.get(index - 1), pixels.get(index - 1 - width), 1) / 2
					+ edgeCost(pixels.get(index), pixels.get(index - width), 1) / 2;
				} else {
					temp.edges[0] = edgeCost(pixels.get(index - 1 - width), pixels.get(index - 1 + width), 2) / 2
							+ edgeCost(pixels.get(index - width), pixels.get(index + width), 2) / 2;
				}
			}
			if (temp.edges[1] != Integer.MAX_VALUE) {
				temp.edges[1] = edgeCost(pixels.get(index - 1 - width), pixels.get(index), Math.sqrt(2));
			}
			if (temp.edges[2] != Integer.MAX_VALUE) {
				if (index % width == 1) {
					temp.edges[2] = edgeCost(pixels.get(index - width), pixels.get(index - 1 - width), 1) / 2
							+ edgeCost(pixels.get(index - 1), pixels.get(index), 1) / 2;
				} else if (index % width == 0) {
					temp.edges[2] = edgeCost(pixels.get(index - width - 2), pixels.get(index - 1 - width), 1) / 2
							+ edgeCost(pixels.get(index - 1), pixels.get(index - 2), 1) / 2;
				} else {
					temp.edges[2] = edgeCost(pixels.get(index - width), pixels.get(index - 2 - width), 2) / 2
							+ edgeCost(pixels.get(index - 2), pixels.get(index), 2) / 2;
				}
			}
			if (temp.edges[3] != Integer.MAX_VALUE) {
				temp.edges[3] = edgeCost(pixels.get(index - width - 1), pixels.get(index - 2), Math.sqrt(2));
			}
			if (temp.edges[4] != Integer.MAX_VALUE) {
				if (index <= width) {
					temp.edges[4] = edgeCost(pixels.get(index - 1), pixels.get(index - 1 + width), 1) / 2
							+ edgeCost(pixels.get(index - 2), pixels.get(index - 2 + width), 1) / 2;
				} else if (index > width * (height - 1)) {
					temp.edges[4] = edgeCost(pixels.get(index - 1), pixels.get(index - 1 - width), 1) / 2
					+ edgeCost(pixels.get(index - 2), pixels.get(index - 2 - width), 1) / 2;
				} else {
					temp.edges[4] = edgeCost(pixels.get(index - 1 - width), pixels.get(index - 1 + width), 2) / 2
							+ edgeCost(pixels.get(index - 2 - width), pixels.get(index - 2 + width), 2) / 2;
				}
			}
			if (temp.edges[5] != Integer.MAX_VALUE) {
				temp.edges[5] = edgeCost(pixels.get(index + width - 1), pixels.get(index - 2), Math.sqrt(2));
			}
			if (temp.edges[6] != Integer.MAX_VALUE) {
				if (index % width == 1) {
					temp.edges[6] = edgeCost(pixels.get(index + width), pixels.get(index - 1 + width), 1) / 2
							+ edgeCost(pixels.get(index - 1), pixels.get(index), 1) / 2;
				} else if (index % width == 0) {
					temp.edges[2] = edgeCost(pixels.get(index + width - 2), pixels.get(index + width - 1), 1) / 2
							+ edgeCost(pixels.get(index - 1), pixels.get(index - 2), 1) / 2;
				} else {
					temp.edges[6] = edgeCost(pixels.get(index - 2), pixels.get(index), 2) / 2
							+ edgeCost(pixels.get(index - 2 + width), pixels.get(index + width), 2) / 2;
				}
			}
			if (temp.edges[7] != Integer.MAX_VALUE) {
				temp.edges[7] = edgeCost(pixels.get(index - 1 + width), pixels.get(index), Math.sqrt(2));
			}
		}
	}
	
	double edgeCost(ImageNodeStructure i1, ImageNodeStructure i2, double d) {
		double dR = (i1.red - i2.red) / d;
		double dG = (i1.green - i2.green) / d;
		double dB = (i1.blue - i2.blue) / d;
		double answer = Math.sqrt((dR * dR + dG * dG + dB * dB) / 3);
		return answer;
	}
	
	/**
	 * 
	 * @param StartX
	 * @param StartY
	 * @param endX
	 * @param endY
	 * @return in the form of {startnode, next, next ... endnode}
	 */
	Coordinate[] getShortestPath(int StartX, int StartY, int endX, int endY) {
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
