package pathfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppPanel extends JPanel implements ActionListener {

	public JLabel createLabel(String labelName) {
		JLabel label = new JLabel();
		label.setText(labelName);
		return label;
	}
	
	public JButton createButton(String buttonLabel, boolean enableActionListener) {
		JButton button = new JButton();
		button.setText(buttonLabel);
		if(enableActionListener)
			button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
