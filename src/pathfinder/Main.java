package pathfinder;

import javax.swing.JFrame;

public final class Main {

	public static void main(String[] args) {
		JFrame myframe = new JFrame("PathFinder");
		Gameplay gameplay = new Gameplay();
		JFrame.setDefaultLookAndFeelDecorated(true);
		myframe.setBounds(10, 10, 600, 600);
		myframe.setVisible(true);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myframe.add(gameplay);
	}

}

