package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

//By default all unimplemented methods
//lead to a Tester
public class Tester {
	JFrame testFrame;
	JLabel newLabel;
	public Tester() {
		newLabel = new JLabel("label");
		doThings();
	}
	
	public Tester(String s) {
		newLabel = new JLabel(s);
		doThings();
	}
	
	public void doThings() {
		testFrame = new JFrame();
		testFrame.setSize(400, 300);
		testFrame.setVisible(true);
		testFrame.add(newLabel);
	}
}
