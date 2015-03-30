import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author lilynguyen
*/

public class ParameterWindow extends JFrame {

	// Fields
	private JPanel panel;
	private JLabel gameEndingLabel, playersLabel;
	private JRadioButton roundsRadio, timeRadio, pointsRadio;
	private JRadioButton pointsRadio1, pointsRadio2, pointsRadio3;
	private JRadioButton player1, player2, player3, player4, player5, player6;
	private ButtonGroup endingGroup, playersGroup;
	private JTextField roundsField, timeField, pointsField;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public ParameterWindow() {

		// Set title
		setTitle("Parameter Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Specify an action for close "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create single panel object that takes up the frame.
		panel = new JPanel();

		createEnding();
		createPlayers();
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set to visible.
		setVisible(true);
	}

	/**
	 *
	 */

	public void createEnding() {
		gameEndingLabel = new JLabel("Game Ending");

		endingGroup = new ButtonGroup();

		roundsRadio = new JRadioButton("Rounds");
		roundsRadio.addActionListener(new EndingRadioListener());

		timeRadio = new JRadioButton("Time");
		timeRadio.addActionListener(new EndingRadioListener());

		pointsRadio = new JRadioButton("Points");
		pointsRadio.addActionListener(new EndingRadioListener());

		// Group the buttons
		endingGroup.add(roundsRadio);
		endingGroup.add(timeRadio);
		endingGroup.add(pointsRadio);

		roundsField = new JTextField(10);

		timeField = new JTextField(10);

		pointsRadio1 = new JRadioButton("100");
		pointsRadio2 = new JRadioButton("200");
		pointsRadio3 = new JRadioButton("Input Own Points");
		pointsField = new JTextField(10);
	}

	/**
	 *
	 */

	public void createPlayers() {
		playersLabel = new JLabel("Number of Players");

		playersGroup = new ButtonGroup();

		player1 = new JRadioButton("1");
		player1.addActionListener(new PlayerRadioListener());

		player2 = new JRadioButton("2");
		player2.addActionListener(new PlayerRadioListener());

		player3 = new JRadioButton("3");
		player3.addActionListener(new PlayerRadioListener());

		player4 = new JRadioButton("4");
		player4.addActionListener(new PlayerRadioListener());

		player5 = new JRadioButton("5");
		player5.addActionListener(new PlayerRadioListener());

		player6 = new JRadioButton("6");
		player6.addActionListener(new PlayerRadioListener());

		playersGroup.add(player1);
		playersGroup.add(player2);
		playersGroup.add(player3);
		playersGroup.add(player4);
		playersGroup.add(player5);
		playersGroup.add(player6);
	}

	public void buildLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(gameEndingLabel)
					.addComponent(roundsRadio)
					.addComponent(timeRadio)
					.addComponent(pointsRadio)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(playersLabel)
					.addComponent(player1)
					.addComponent(player2)
					.addComponent(player3)
					.addComponent(player4)
					.addComponent(player5)
					.addComponent(player6)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(gameEndingLabel)
				.addComponent(roundsRadio)
				.addComponent(timeRadio)
				.addComponent(pointsRadio)
				.addComponent(playersLabel)
				.addGroup(layout.createParallelGroup()
					.addComponent(player1)
					.addComponent(player2)
					.addComponent(player3)
					.addComponent(player4)
					.addComponent(player5)
					.addComponent(player6)
				)
		);
	}

	private class EndingRadioListener implements ActionListener {

		String input;

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == roundsRadio) {
				input = roundsField.getText();
			}
			else if (e.getSource() == timeRadio) {
				input = timeField.getText();
			}
			else if (e.getSource() == pointsRadio) {
				pointsRadio1.addActionListener(new PointsRadioListener());
				pointsRadio2.addActionListener(new PointsRadioListener());
				pointsRadio3.addActionListener(new PointsRadioListener());
			}
		}
	}

	private class PointsRadioListener implements ActionListener {

		String input;

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == pointsRadio1) {
				input = "100";
			}
			else if (e.getSource() == pointsRadio2) {
				input = "200";
			}
			else if (e.getSource() == pointsRadio3) {
				input = pointsField.getText();
			}
		}
	}

	private class PlayerRadioListener implements ActionListener {

		String input;

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == player1) {
				input = "1";
			}
			else if (e.getSource() == player2) {
				input = "2";
			}

			else if (e.getSource() == player3) {
				input = "3";
			}

			else if (e.getSource() == player4) {
				input = "4";
			}

			else if (e.getSource() == player5) {
				input = "5";
			}

			else if (e.getSource() == player6) {
				input = "6";
			}
		}
	}

	public static void main(String[] args) {
		new ParameterWindow();
	}
}