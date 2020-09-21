package pathfinder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Gameplay extends AppPanel implements ActionListener {
	private JButton[][] squares = new JButton[15][15];
	private JButton begin;
	private JButton reset;

	private JPanel gamefield;

	private JSlider widthSelector;
	private JSlider heightSlector;

	private final String beginButtonText = "Begin";
	private final String resetButtonText = "Reset";
	private final String entranceSquareText = "Entrance";
	private final String exitSquareText = "Exit";

	Gameplay() {
		controllerInit();
		gamefieldInit();
	}

	private void controllerInit() {
		begin = super.createButton(beginButtonText, true);
		reset = super.createButton(resetButtonText, true);
		widthSelector = new JSlider(JSlider.HORIZONTAL, 15, 15);
		heightSlector = new JSlider(JSlider.HORIZONTAL, 15, 15);
		add(begin);
		add(reset);
		add(widthSelector);
		add(heightSlector);
	}

	private void gamefieldInit() {
		gamefield = new JPanel();
		gamefield.setLayout(new GridLayout(15, 15));
		for (int i = 0; i < 15; ++i) {
			for (int k = 0; k < 15; ++k) {
				squares[i][k] = new JButton();
				if (i == 0 && k == 0)
					squares[i][k].setText(entranceSquareText);
				else if (i == 14 && k == 14)
					squares[i][k].setText(exitSquareText);
				else
					squares[i][k].addActionListener(this);
				gamefield.add(squares[i][k]);
			}
		}
		add(gamefield);
	}

	private char[][] readGamefield() {
		char[][] boardReadings = new char[15][15];
		for (int i = 0; i < 15; ++i) {
			for (int k = 0; k < 15; ++k) {
				if (squares[i][k].getText().equals(entranceSquareText))
					boardReadings[i][k] = 'I';
				else if (squares[i][k].getText().equals(exitSquareText))
					boardReadings[i][k] = 'O';
				else
					boardReadings[i][k] = ' ';
			}
		}
		return boardReadings;
	}

	private void runSearch() {
		DepthFirstSearch dfs = new DepthFirstSearch(readGamefield());
		List<Point> path = dfs.getPath();
		for (Point p : path) {
			squares[p.x][p.y].setBackground(Color.GREEN);
		}
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (beginWasClicked(e))
			runSearch();
		else if (resetWasClicked(e))
			resetBoard();
	}

	private void resetBoard() {
		remove(gamefield);
		gamefieldInit();
		revalidate();
	}

	private boolean beginWasClicked(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		if (button.getText().equals(beginButtonText))
			return true;
		else
			return false;
	}

	private boolean resetWasClicked(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		if (button.getText().equals(resetButtonText))
			return true;
		else
			return false;
	}
}