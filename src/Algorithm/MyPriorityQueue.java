package Algorithm;

public class MyPriorityQueue {
	Node n;
	MyPriorityQueue nextQ;
	
	public MyPriorityQueue() {
		nextQ = null;
		n = null;
	}
	
	private void setNode(Node m) {
		n = m;
	}
	
	private Node getNode() {
		return n;
	}
	
	private MyPriorityQueue getNextQ() {
		return nextQ;
	}
	
	private void setNext(MyPriorityQueue q) {
		nextQ = q;
	}
	
	public void add(Node m) {
		if (nextQ == null) {
			n = m;
			nextQ = new MyPriorityQueue();
		} else {
			if (n == null) {
				n = m;
			}
			if (m.cost >= n.cost) {
				nextQ.add(m);
			} else {
				MyPriorityQueue newQ = new MyPriorityQueue();
				newQ.setNode(n);
				newQ.setNext(nextQ);
				setNode(m);
				setNext(newQ);
			}
		}
	}
	
	public Node poll() {
		Node answer = n;
		setNode(nextQ.getNode());
		setNext(nextQ.getNextQ());
		return answer;
	}
	
	public boolean isEmpty() {
		if (nextQ == null) {
			return true;
		} else return false;
	}
}
