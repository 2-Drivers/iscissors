package Algorithm;

import java.awt.Color;

/**
 * you can change the implementation or internal structure
 * in whatever way you like
 * same for the ImageNodeStructure class
 */
public class ImageNodeStructure {
	public double[] edges;
	public final int red;
	public final int green;
	public final int blue;
	
	ImageNodeStructure(Color c) {
		red = c.getRed();
		green = c.getGreen();
		blue = c.getBlue();
		edges = new double[8];
		for (int i = 0; i < 8; ++i) {
			edges[i] = 0;
		}
	}
}
