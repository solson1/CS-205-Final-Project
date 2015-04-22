import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * @author lilynguyen
 */

/**
NOTES ABOOT THE GAME
Do you want to create action listeners for the type of game ending, then the specific ending they choose
(out of the presets) or do you just want to return the game ending like rounds, time, or points and then
process the type of game ending on the receiving end using a bunch of switchs/if elses ie if it's 10, 15,
20, it was a rounds ending or something like that.
*/

public class ParameterWindow extends JFrame {

	// Fields
	private JPanel panel;
	private JLabel gameEndingLabel, playersLabel,
				   parameterLogo;
	private JRadioButton roundsRadio, timeRadio, pointsRadio,
											 roundsRadio1, roundsRadio2, roundsRadio3,
											 timeRadio1, timeRadio2, timeRadio3,
											 pointsRadio1, pointsRadio2,
											 cpu1, cpu2, cpu3;
	private JButton playButton;
	private ButtonGroup endingGroup, roundsGroup, timeGroup, pointsGroup, playersGroup;
//	private String endingInput, 
	private int cpuPlayers;

//	private GameType type;
//	private int numPlayers;
//	private String[] players;

// private GameState gameState;

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
		createParameterLogo();
		createPlayButton();
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

		// Create label for game ending
		gameEndingLabel = new JLabel("Game Ending");

		// Create button grouping for ending radio buttons
		endingGroup = new ButtonGroup();

		// Create buttons for three types of endings
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

		// Create radio buttons for rounds
		roundsGroup = new ButtonGroup();
		roundsRadio1 = new JRadioButton("10");
		roundsRadio2 = new JRadioButton("15");
		roundsRadio3 = new JRadioButton("20");
		roundsGroup.add(roundsRadio1);
		roundsGroup.add(roundsRadio2);
		roundsGroup.add(roundsRadio3);

		roundsRadio1.setVisible(false);
		roundsRadio2.setVisible(false);
		roundsRadio3.setVisible(false);

		// Create radio buttons for time
		timeGroup = new ButtonGroup();
		timeRadio1 = new JRadioButton("5 Minutes");
		timeRadio2 = new JRadioButton("10 Minutes");
		timeRadio3 = new JRadioButton("20 Minutes");
		timeGroup.add(timeRadio1);
		timeGroup.add(timeRadio2);
		timeGroup.add(timeRadio3);

		timeRadio1.setVisible(false);
		timeRadio2.setVisible(false);
		timeRadio3.setVisible(false);

		// Create radio buttons and input field for points
		pointsGroup = new ButtonGroup();
		pointsRadio1 = new JRadioButton("100");
		pointsRadio2 = new JRadioButton("200");
		pointsGroup.add(pointsRadio1);
		pointsGroup.add(pointsRadio2);

		pointsRadio1.setVisible(false);
		pointsRadio2.setVisible(false);
	}

	/**
	 *
	 */

	public void createPlayers() {

		// Create label for number of players
		playersLabel = new JLabel("Number of CPU Players");

		// Create button grouping for player radio buttons
		playersGroup = new ButtonGroup();

		cpu1 = new JRadioButton("1");
		cpu1.addActionListener(new PlayerRadioListener());

		cpu2 = new JRadioButton("2");
		cpu2.addActionListener(new PlayerRadioListener());

		cpu3 = new JRadioButton("3");
		cpu3.addActionListener(new PlayerRadioListener());

		// Group the buttons
		playersGroup.add(cpu1);
		playersGroup.add(cpu2);
		playersGroup.add(cpu3);
	}

	/**
	 *
	 */

	public void createParameterLogo() {
		parameterLogo = new JLabel(new ImageIcon("images/parameters.png"));
	}

	/**
	 *
	 */

	public void createPlayButton() {
		playButton = new JButton("PLAY!");
		playButton.addActionListener(new PlayButtonListener());
	}

	public void buildLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(150,150,150)
						.addComponent(parameterLogo)
					)
				.addGroup(layout.createSequentialGroup()
					.addGap(150,150,150)
					.addComponent(gameEndingLabel)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(200,200,200)
					.addComponent(roundsRadio)
					.addComponent(timeRadio)
					.addComponent(pointsRadio)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(200,200,200)
					.addComponent(roundsRadio1)
					.addComponent(roundsRadio2)
					.addComponent(roundsRadio3)
					.addComponent(timeRadio1)
					.addComponent(timeRadio2)
					.addComponent(timeRadio3)
					.addComponent(pointsRadio1)
					.addComponent(pointsRadio2)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(150,150,150)
					.addComponent(playersLabel)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(200,200,200)
					.addComponent(cpu1)
					.addComponent(cpu2)
					.addComponent(cpu3)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(315,315,315)
					.addComponent(playButton)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(70,70,70)
					.addComponent(parameterLogo)
					.addGap(40,40,40)
				)
				.addComponent(gameEndingLabel)
				.addGroup(layout.createParallelGroup()
					.addComponent(roundsRadio)
					.addComponent(timeRadio)
					.addComponent(pointsRadio)
					.addGap(5,5,5)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(roundsRadio1)
					.addComponent(roundsRadio2)
					.addComponent(roundsRadio3)
					.addComponent(timeRadio1)
					.addComponent(timeRadio2)
					.addComponent(timeRadio3)
					.addComponent(pointsRadio1)
					.addComponent(pointsRadio2)
					.addGap(70,70,70)
				)
				.addComponent(playersLabel)
				.addGroup(layout.createParallelGroup()
					.addComponent(cpu1)
					.addComponent(cpu2)
					.addComponent(cpu3)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(60,60,60)
					.addComponent(playButton)
				)
		);
	}

	private class EndingRadioListener implements ActionListener {

		String input;

		public void actionPerformed(ActionEvent e) {

			// If the radio button is active on ROUNDS
			if (e.getSource() == roundsRadio) {

				// Display buttons
				roundsRadio1.setVisible(true);
				roundsRadio2.setVisible(true);
				roundsRadio3.setVisible(true);

				// Make sure others are not visible
				timeRadio1.setVisible(false);
				timeRadio2.setVisible(false);
				timeRadio3.setVisible(false);
				pointsRadio1.setVisible(false);
				pointsRadio2.setVisible(false);

				// Add action listeners to the buttons
//				roundsRadio1.addActionListener(new EndingParamRadioListener());
//				roundsRadio2.addActionListener(new EndingParamRadioListener());
//				roundsRadio3.addActionListener(new EndingParamRadioListener());
			
				type = ROUNDS;
			}

			// Else if radio button is active on TIME
			else if (e.getSource() == timeRadio) {

				// Display buttons
				timeRadio1.setVisible(true);
				timeRadio2.setVisible(true);
				timeRadio3.setVisible(true);

				// Make sure others are not visible
				roundsRadio1.setVisible(false);
				roundsRadio2.setVisible(false);
				roundsRadio3.setVisible(false);
				pointsRadio1.setVisible(false);
				pointsRadio2.setVisible(false);

				// Add action listeners to the buttons
//				timeRadio1.addActionListener(new EndingParamRadioListener());
//				timeRadio2.addActionListener(new EndingParamRadioListener());
//				timeRadio3.addActionListener(new EndingParamRadioListener());

				type = TIMED_GAME;
			}

			// Else if radio button is active on POINTS
			else if (e.getSource() == pointsRadio) {

				// Display buttons
				pointsRadio1.setVisible(true);
				pointsRadio2.setVisible(true);

				// Make sure others are not visible
				timeRadio1.setVisible(false);
				timeRadio2.setVisible(false);
				timeRadio3.setVisible(false);
				roundsRadio1.setVisible(false);
				roundsRadio2.setVisible(false);
				roundsRadio3.setVisible(false);

				// Add action listeners to the buttons
//				pointsRadio1.addActionListener(new EndingParamRadioListener());
//				pointsRadio2.addActionListener(new EndingParamRadioListener());

				type = ELIMINATION;
			}
		}
	}

	// private class EndingParamRadioListener implements ActionListener {

	// 	public void actionPerformed(ActionEvent e) {

	// 		if (e.getSource() == roundsRadio1)  endingInput = "10"; 
	// 		else if (e.getSource() == roundsRadio2) endingInput = "15";
	// 		else if (e.getSource() == roundsRadio3) endingInput = "20";
	// 		else if (e.getSource() == timeRadio1) endingInput = "5";
	// 		else if (e.getSource() == timeRadio2) endingInput = "10";
	// 		else if (e.getSource() == timeRadio3) endingInput = "20";
	// 		else if (e.getSource() == pointsRadio1) endingInput = "100";
	// 		else if (e.getSource() == pointsRadio2) endingInput = "200";
	// 	}
	// }

	private class PlayerRadioListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == cpu1) {
				cpuPlayers = 1;
				players = new String[2];
				players[0] = "USERNAME";
				players[1] = "CPU1";
			}
			else if (e.getSource() == cpu2) {
				cpuPlayers = 2;
				players = new String[3];
				players[0] = "USERNAME";
				players[1] = "CPU1";
				players[2] = "CPU2";
			}
			else if (e.getSource() == cpu3) {
				cpuPlayers = 3;
				players = new String[4];
				players[0] = "USERNAME";
				players[1] = "CPU1";
				players[2] = "CPU2";
				players[3] = "CPU3";
			}
			numPlayers = cpuPlayers + 1;
		}
	}

	private class PlayButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// Go to gameboard
				new Gameboard();
				setVisible(false);

				// Send game things to thing here
//				gameState = gameWrapper.startNewGame(type, numPlayers, players);				
			}
	}

	public static void main(String[] args) {
		new ParameterWindow();
	}
}