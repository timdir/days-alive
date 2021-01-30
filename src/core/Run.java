package core;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;    


public class Run {
	
	public static void main(String[] args) {

	JFrame frame = new JFrame("Days Alive");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	Container c = frame.getContentPane();
	GUI gui = new GUI();
	gui.addComponentToPane(c);

	frame.getContentPane().setPreferredSize(
			new Dimension(280, 100));
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	}
	
}   



