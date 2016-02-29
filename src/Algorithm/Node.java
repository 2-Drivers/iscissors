package Algorithm;

import java.awt.Color;

/**
 * you can change the implementation or internal structure
 * in whatever way you like
 * same for the ImageNodeStructure class
 */
public class Node { //implements Comparable<Node>{
	public double[] edges;
	public double[] costs;
	public final int red;
	public final int green;
	public final int blue;
	public Node predecessor;
	public int state;
	public int x, y;
	public double cost;
	Node(Color c, int xx, int yy) {
		x = xx;
		y = yy;
		red = c.getRed();
		green = c.getGreen();
		blue = c.getBlue();
		edges = new double[8];
		for (int i = 0; i < 8; ++i) {
			edges[i] = 0;
		}
		costs = new double[8];
		for (int i = 0; i < 8; ++i) {
			costs[i] = 0;
		}
		state = 0;
		predecessor = null;
		cost = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Node getPredecessor() {
		return predecessor;
	}

	/*
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}
	*/
}
