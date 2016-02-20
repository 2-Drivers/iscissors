package Algorithm;

/**
 * this is a simple coordinate pair
 * you can only initialize it when you construct
 * not in the process
 * 
 * @author JX
 *
 */
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}
