package Algorithm;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

public class ImageStructure {
	Vector<Node> pixels; //see its definition
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
		
		pixels = new Vector<Node>();
		initializeAllThroughImage(i);
	}
	
	private void initializeAllThroughImage(BufferedImage i) {
		for (int j = 0; j < height; ++j) {
			for (int k = 0; k < width; ++k) {
				Color pixelValueARGB = new Color(i.getRGB(k,j));
				pixels.add(new Node(pixelValueARGB, k, j));
			}
		}
		computeEdges();
	}
	
	private void computeEdges() {
		Iterator<Node> it = pixels.iterator();
		int index = 0;
		while (it.hasNext()) {
			index += 1;
			Node temp = it.next();
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
			int maxD = 256;
			for (int ii = 0; ii <= 7; ++ii)
			{
				if (temp.edges[ii] > Integer.MAX_VALUE - 3)
				{
					temp.costs[ii] = 0;
				}
				else if (ii % 2 == 0)
				{
					temp.costs[ii] = maxD - temp.edges[ii];
				}
				else {
					temp.costs[ii] = (maxD - temp.edges[ii]) / Math.sqrt(2);
				}
			}
		}
	}
	
	double edgeCost(Node i1, Node i2, double d) {
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
		buildMiniCostTree(StartX, StartY);
		Vector<Node> path = new Vector<Node>();
		Node start = pixels.get(StartX * height + StartY * width);
		while (start.predecessor != null)
		{
			path.add(start);
			start = start.predecessor;
		}
		path.add(start);
		
		return null;
	}

	Vector<Node> getNeighbors(int x, int y)	{
		
		Vector<Node> neighbors = new Vector<Node>();
		
		for (int xx = -1; xx <= 1; ++xx)
		{
			for (int yy = -1; yy <= 1; ++yy)
			{
				if (x + xx < 0 || x + xx >= width || y + yy < 0 || y + yy >= height)
					continue;
				//neighbors.add(pixels.get((x + xx) * height + (y + yy) * width)); //?
				neighbors.add(pixels.get((y + yy) * width + x + xx));
			}
		}
		
		return neighbors;
		
	}
	
	int link(Node start, Node end)
	{
		int dx = end.x - start.x; ///by default it is private;
		int dy = end.y - start.y;
		
		if (dx == 1 && dy == 0) return 0;
		if (dx == 1 && dy == -1) return 1;
		if (dx == 0 && dy == -1) return 2;
		if (dx == -1 && dy == -1) return 3;
		if (dx == -1 && dy == 0) return 4;
		if (dx == -1 && dy == 1) return 5;
		if (dx == 0 && dy == 1) return 6;
		if (dx == 1 && dy == 1) return 7;
		System.out.println(end.x);
		System.out.println(start.x);
		System.out.println(end.y);
		System.out.println(start.y);
		return -1;
	}
	
	public void buildMiniCostTree (int StartX, int StartY)
	{
		//PriorityQueue<Node> pq = new PriorityQueue<Node>();
		MyPriorityQueue pq = new MyPriorityQueue();
		//initialize each node to the INITIAL state, i.e. state = 0
		Iterator<Node> it = pixels.iterator();
		while (it.hasNext())
		{ 
			Node temp = it.next();
			temp.state = 0;
		}
		
		//Node seed = pixels.get(StartX*height + StartY*width);
		Node seed = pixels.get(StartX + (StartY - 1) * width);
		seed.predecessor = null;
		seed.cost = 0;
		pq.add(seed);
		
		while (!pq.isEmpty())
		{
			Node q = pq.poll();
			q.state = 2; //state = 2 means EXPANDED
			Vector<Node> neighbors = getNeighbors(q.x, q.y); ///??????????????????
			Iterator<Node> r = neighbors.iterator();
			while (r.hasNext())
			{
				Node temp = r.next();
				if (temp.state == 0) // state INITIAL
				{
					temp.predecessor = q;
					temp.cost = q.cost + q.costs[link(q,temp)]; //?*2
					temp.state = 1;
					pq.add(temp);
				}
				else if (temp.state == 1) // state ACTIVE
				{
					if (q.cost + q.costs[link(q,temp)] < temp.cost)
					{
						temp.predecessor = q;
						temp.cost = q.cost + q.costs[link(q,temp)];
					}
				}
			}
		}
	}
	
	public Vector<Coordinate> getPath(Coordinate c1, Coordinate c2) {
		int startX, startY, endX, endY;
		startX = c1.x();
		startY = c1.y();
		endX = c2.x();
		endY = c2.y();
		
		buildMiniCostTree(startX, startY);
		Vector<Node> path = new Vector<Node>();
		Node end = pixels.get(endX + (endY - 1) * width);
		while (end.predecessor != null)
		{
			path.add(end);
			end = end.predecessor;
			//System.out.println("1");
		}
		path.add(end);
		
		System.out.println("zzzzzz");
		System.out.println(path.size());
		
		Vector<Coordinate> answer = new Vector<Coordinate>();
		Iterator<Node> it =  path.iterator();
		while (it.hasNext()) {
			Node c = it.next();
			answer.add(new Coordinate(c.x, c.y));
		}
		return answer;
	}
}
