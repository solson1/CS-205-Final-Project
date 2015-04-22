//package GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.Timer;

import utilityObjects.*;

/********************
 * Lily Nguyen		*
 * CS 205			*
 * Final Project	*
 * Rat-A-Tat Cat	*
 ********************/

/**
 * @author lilynguyen
 */

public class GUI{

	private GameWrapper gameWrapper;
	private GameState gameState;
	private String[] players;

	public static void main(String[] args) {
		GUI game = new GUI();
		game.start();
	}
	public void start()  { 
		 gameWrapper = new GameWrapper();
		//new ParameterWindow();
		try {
		Thread.sleep(4000);
		new InstructionWindow(); 
		} catch (FileNotFoundException e) {
			//
		 } 
		 catch (InterruptedException e) {
		 	//
		 }
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * This class is the INSTRUCTIONS window class.
 */
public class InstructionWindow extends JFrame {

	// Component declarations
	private JPanel panel;
	private JLabel logoLabel;
	private JTextArea infoTextArea;
	private JButton nextButton;
	private JScrollPane scrollPane;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public InstructionWindow() throws FileNotFoundException {

		// Set window title
		setTitle ("Instruction Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Set "x" allowing program to terminate
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create single panel object that takes up the frame.
		panel = new JPanel();

		createLogo();
		createInfoBox();
		createNextButton();
		buildLayout();
		
		// Add panel to frame
		add(panel);

		// Set to visible.
		setVisible(true);
	} // End InstructionWindow constructor

	/**
	 * This method creates a label for the game logo
	 */
	public void createLogo() {
		logoLabel = new JLabel(new ImageIcon("images/logo.png"));
	} // End createLogo

	/**
	 * This method creates a text area object containing all the information on gameplay.
	 */
	public void createInfoBox() throws FileNotFoundException {

		// Initialize string that will go into text area
		String gameInfo = "";

		// FILENAME that hold info about game to be displated
		String filename = "gameDesc.txt";

		// Open file
		File file = new File(filename);
		Scanner infile = new Scanner(file);

		while (infile.hasNext()) {
			gameInfo += infile.nextLine()+"\n";
		}

		infoTextArea = new JTextArea(5, 20);
		infoTextArea.setEditable(false);
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);

		scrollPane = new JScrollPane();
		infoTextArea.setText(gameInfo);
		scrollPane.setViewportView(infoTextArea);
	} // End createInfoBox

	/**
	 * This method creates a button to move on to the menu after user is done reading.
	 * This button makes the program go to the MENU window next.
	 */
	public void createNextButton() {
		nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());
	} // End createNextButton

	/**
	 * This method uses the fields to create the layout of the window.
	 */
	public void buildLayout() {

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGap(75,75,75)
					.addComponent(logoLabel)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(75,75,75)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(75,75,75)
					.addComponent(nextButton)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(40,40,40)
				.addComponent(logoLabel)
				.addGap(40,40,40)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				.addGap(20,20,20)
				.addComponent(nextButton)
		);
	} // End buildLayout

	private class NextButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Continue to the player list
			new PlayerListWindow();
			setVisible(false);
		} // End actionPerformed
	} // End NextButtonListener
} // End InstructionWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * This class is the PLAYER LIST window
 */
public class PlayerListWindow extends JFrame {

	// Component declarations
	private JPanel panel;
	//private JList<String> playerList;
	private JList playerList;
	private JButton okayButton, deleteButton, addButton, statsButton;
	private JScrollPane playerScrollPane;
	private JLabel playerLogoLabel;
	private String nameSelection;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public PlayerListWindow() {

		// Set window title
		setTitle ("Player List Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Set "x" allowing program to terminate
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create single panel object that takes up the frame.
		panel = new JPanel();

		createPlayerList();
		createButtons();
		createPlayerLogo();
		buildLayout();
		
		// Add panel to frame
		add(panel);

		// Set to visible
		setVisible(true);
	} // End PlayerListWindow constructor

	/**
	 * This method creates the player list combo box for username selection
	 */
	public void createPlayerList() {

		// THIS IS GOING TO BE A LOOP READING FROM THE DATABASE ALL THE USERNAMES THAT IT HOLDS
		// THIS IS HOW THE LIST WILL ALWAYS BE UPDATED...
		String[] players = {"NAME","NAME","NAME","NAME"};

		//linkedListPlayers = gameWrapper.getProfiles();
		//turn linkedListPlayers into an array list...or...ya...

		// Create list componenet with values from list in it
		//playerList = new JList<>(players);
		playerList = new JList(players);

		// Put player list into scroll pane
		playerScrollPane = new JScrollPane(playerList);

		// Selection selection mode
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Register the list selection listener
		playerList.addListSelectionListener(new ListListener());
	} // End createPlayerList

	/**
	 * This method creates the buttons below the username list for different account options
	 */
	public void createButtons() {

		// Assume selected name profile button
		okayButton = new JButton("OKAY");
		okayButton.addActionListener(new ButtonListener());

		// Delete selected name profile from database button
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ButtonListener());

		// Add new username button
		addButton = new JButton("Add");
		addButton.addActionListener(new ButtonListener());

		// View statistics of selected name button
		statsButton = new JButton("Stats");
		statsButton.addActionListener(new ButtonListener());
	} // End createButtons

	/**
	 * This method creates the window logo
	 */
	public void createPlayerLogo() {
		playerLogoLabel = new JLabel(new ImageIcon("images/playerlist.png"));
	} // End createPlayerLogo

	/**
	 * This method creates the popup windows for errors in not selecting usernames for certain functions
	 */
	public void nameSelectError() {
		String message = "You must select a name from the list.";
		String title = "Error";
		ImageIcon icon = new ImageIcon("images/error.png");

		// Prompt a pop up asking for user to select a username.
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, icon);
	} // End nameSelectError

	/**
	 * This method builds the window layout
	 */
	public void buildLayout() {

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGroup(layout.createSequentialGroup()
						.addComponent(playerLogoLabel)
					)
					.addGroup(layout.createSequentialGroup()
						.addGap(100,100,100)
						.addComponent(playerScrollPane)
						.addGap(100,100,100)
					)
					.addGroup(layout.createSequentialGroup()
						.addComponent(okayButton)
						.addComponent(deleteButton)
						.addComponent(addButton)
						.addComponent(statsButton)
					)
		);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createSequentialGroup()
						.addGap(70,70,70)
						.addComponent(playerLogoLabel)
					)
					.addGroup(layout.createSequentialGroup()
						.addGap(60,60,60)
						.addComponent(playerScrollPane)
						.addGap(50,50,50)
					)
					.addGroup(layout.createParallelGroup()
						.addComponent(okayButton)
						.addComponent(deleteButton)
						.addComponent(addButton)
						.addComponent(statsButton)
						.addGap(100,100,100)
					)
		);
	} // End buildLayout

	/**
	 * This class responds to the selected username in the list box
	 */
	private class ListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			// Get selected name, assign to variable
			nameSelection = (String) playerList.getSelectedValue();
		} // End valueChanged
	} // End ListListener class

	/**
	 * This class responds to the option buttons for accounts
	 */
	private class ButtonListener implements ActionListener {

		String usernameNew;
		int deleteReply;

		public void actionPerformed(ActionEvent e) {

			// Evaluate which button was pressed
			if (e.getSource() == okayButton) {
				if (nameSelection == null) {
					// if no name is selected, error occurs
					nameSelectError();
				}
				else {
					// ASSUME selection PROFILE
					//gameWrapper.login(nameSelection)

					// Go back to main menu
					new MenuWindow();
					setVisible(false);
				}
			}
			else if (e.getSource() == deleteButton) {
				if (nameSelection == null) {
					// if no name is selected, error occurs
					nameSelectError();
				}
				else {
					String message = "Are you sure you want to delete this profile?";
					String title = "Delete User Profile";
					//ImageIcon icon = new ImageIcon("");
					deleteReply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (deleteReply == JOptionPane.YES_OPTION) {
						// DELETE selection USER
					}
				}
			}
			else if (e.getSource() == addButton) {
				// Get the username
				usernameNew = JOptionPane.showInputDialog("Please enter a username:");

				// ADD NEW USER
				gameWrapper.createProfile(usernameNew);
			}
			else if (e.getSource() == statsButton) {
				if (nameSelection == null) {
					// if no name is selected, error occurs
					nameSelectError();
				}
				else {
					// VIEW STATISTICS OF USER
					// Need to read input of the value highlighted
					new StatWindow();
					setVisible(false);
				}
			}
		} // End actionPerformed
	} // End ButtonListener class
} // End PlayerListWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * This is the MENU window
 */
public class MenuWindow extends JFrame {

	// Component declarations
	private JPanel panel;
	private JLabel logoLabel, nameLabel;
	private JButton playersButton, playButton, exitButton;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public MenuWindow() {

		// Set window title
		setTitle ("Menu Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Set "x" allowing program to terminate
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CReate single panel object that takes up frame
		panel = new JPanel();

		createLogo();
		createButtons();
		createNameLabel();
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set to visible
		setVisible(true);

	} // End MenuWindow constructor

	/**
	 * This method creates a label for the game logo
	 */
	public void createLogo() {
		logoLabel = new JLabel(new ImageIcon("images/logo.png"));
	} // End createLogo

	/**
	 * This method creates the menu buttons 
	 */
	public void createButtons() {
		playersButton = new JButton("Change Player");
		playersButton.addActionListener(new ButtonListener());

		playButton = new JButton("PLAY GAME!");
		playButton.addActionListener(new ButtonListener());

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ButtonListener());
	} // End createButtons

	/**
	 * This method creates the welcome label for the username.
	 */
	public void createNameLabel() {
		nameLabel = new JLabel("Welcome "); // +gameWrapper.playerID
	} // End createNameLabel

	/**
	 *	This method uses the fields to create the layout of the window.
	 */
	public void buildLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGap(700,700,700)
				.addComponent(logoLabel)
				.addComponent(nameLabel)
				.addComponent(playersButton)
				.addComponent(playButton)
				.addComponent(exitButton)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(40,40,40)
				.addComponent(logoLabel)
				.addGap(30,30,30)
				.addComponent(nameLabel)
				.addGap(40,40,40)
				.addComponent(playersButton)
				.addGap(25,25,25)
				.addComponent(playButton)
				.addGap(25,25,25)
				.addComponent(exitButton)
		);
	} // End buildLayout

	/**
	 * This ActionListener goes to the player window page.
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == playersButton) {
				new PlayerListWindow();
				setVisible(false);
			}
			else if (e.getSource() == playButton) {
				new ParameterWindow();
				setVisible(false);
			}
			else if (e.getSource() == exitButton) {
				System.exit(0);
			}
		} // End actionPerformed
	} // End ButtonListener
} // End MenuWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * This class is the PARAMETERS window
 */
public class ParameterWindow extends JFrame {

	// Component declarations
	private JPanel panel;
	private JLabel gameEndingLabel, playersLabel,
				   parameterLogo;
	private JRadioButton roundsRadio, timeRadio, pointsRadio,
					 cpu1, cpu2, cpu3;
	private JButton playButton;
	private ButtonGroup endingGroup, playersGroup;

	private int cpuPlayers;

	private int numPlayers;
	//private String[] players;

	private GameType type;

//private int type = -1;

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
	} // End ParameterWindow constructor

	/**
	 * This method creates the buttons for the game parameters.
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
	} // End createEnding

	/**
	 * This method creates the buttons for choosing number of players
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
	} // End createPlayers

	/**
	 * This method creates the logo for the window
	 */
	public void createParameterLogo() {
		parameterLogo = new JLabel(new ImageIcon("images/parameters.png"));
	} // End createParameterLogo

	/**
	 * This method creates the play button to continue the game
	 */
	public void createPlayButton() {
		playButton = new JButton("PLAY!");
		playButton.addActionListener(new PlayButtonListener());
	} // End createPlayButton

	/**
	 * This method builds the layout of the window
	 */
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
				)
				.addGap(50,50,50)
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
	} // End buildLayout

	/**
	 * This class responds to the ending radio buttons being selected
	 */
	private class EndingRadioListener implements ActionListener {

		String input;

		public void actionPerformed(ActionEvent e) {

			// If the radio button is active on ROUNDS
			if (e.getSource() == roundsRadio) {
				type = GameType.ROUNDS;
			}

			// Else if radio button is active on TIME
			else if (e.getSource() == timeRadio) {
				type = GameType.TIMED_GAME;
			}

			// Else if radio button is active on POINTS
			else if (e.getSource() == pointsRadio) {
				type = GameType.ELIMINATION;
			}
		} // End actionPerformed
	} // End EndingRadioListener

	/**
	 * This class responds to the player number radio buttons being selected
	 */ 
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
		} // End actionPerformed
	} // End PlayerRadioListener

	private class PlayButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// If game type not specified or players, error
				if ((type == GameType.ROUNDS || type == GameType.ELIMINATION || type == GameType.TIMED_GAME) && (cpuPlayers == 1 || cpuPlayers == 2 || cpuPlayers == 3)) {
					// Create a new game and initialize the gameState
					gameState = gameWrapper.startNewGame(type, players);
					

					// Go to gameboard
					new Gameboard();
					setVisible(false);	
				}
				else {
					JOptionPane.showMessageDialog(null, "You need to specify a game type and number of players.");
					System.out.print(type);		
				}	
			} // End PlayButtonListener
	} // End PlayButtonListener
} // End ParameterWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class StatWindow extends JFrame {

	private JLabel statisticsLogo, winLabel, lossLabel, playedLabel;
	private JPanel panel;

	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	//private JLabel gamesPlayed, gamesWon, gamesLost;

	public StatWindow() {

		// Set title
		setTitle("Statistics Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Specify an action for close "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create single panel object that takes up the frame.
		panel = new JPanel();

		createLogo();
		createContent();
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set to visible.
		setVisible(true);

	}

	public void createLogo() {
		statisticsLogo = new JLabel(new ImageIcon("images/statistics.png"));
	}

	public void createContent() {

		// Get statistics of the username from the database
		// String stats = 
		winLabel = new JLabel("WINS: ");
		lossLabel = new JLabel("LOSS: ");
		playedLabel = new JLabel("Games Played: ");
	}

	public void buildLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGap(700,700,700)
					.addComponent(statisticsLogo)
					.addComponent(winLabel)
					.addComponent(lossLabel)
					.addComponent(playedLabel)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(70,70,70)
				.addComponent(statisticsLogo)
				.addGap(40,40,40)
				.addComponent(winLabel)
				.addComponent(lossLabel)
				.addComponent(playedLabel)
		);
	}
} // End StatWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class HelpWindow extends JFrame {

	// Fields
	private JPanel panel;
	private JTextArea infoTextArea;
	private JScrollPane scrollPane;
	private JButton okayButton;

	// Constats -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public HelpWindow() throws FileNotFoundException {

		// Set window title
		setTitle("omgah I need help");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Center window
		setLocationRelativeTo(null);

		// Set "x" allowing program to terminate
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create single panel object that takes up frame
		panel = new JPanel();

		createInfoBox();
		createOkButton();
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set visible
		setVisible(true);
	} // End HelpWindow constructor

	public void createInfoBox() throws FileNotFoundException {
		// Initialize string that will go into text area
		String gameInfo = "";

		// FILENAME that hold info about game to be displated
		String filename = "gameDesc.txt";

		// Open file
		File file = new File(filename);
		Scanner infile = new Scanner(file);

		while (infile.hasNext()) {
			gameInfo += infile.nextLine()+"\n";
		}

		infoTextArea = new JTextArea(5, 20);
		infoTextArea.setEditable(false);
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);

		scrollPane = new JScrollPane();
		infoTextArea.setText(gameInfo);
		scrollPane.setViewportView(infoTextArea);
	} // End createInfoBox

	public void createOkButton() {
		okayButton = new JButton("ya sure dode gotcha");
		okayButton.addActionListener(new ButtonListener());
	}

	public void buildLayout() {

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addGap(75,75,75)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(75,75,75)
					.addComponent(okayButton)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(40,40,40)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(25,25,25)
					.addComponent(okayButton)
				)
		);
	} // End buildLayout

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dispose();
		} // End actionPerformed
	} // End ButtonListener
} // End HelpWindow
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Gameboard extends JFrame {

	// Fields
	private JPanel panel, centerPanel, topLeftPanel, bottomRightPanel, centerCenterPanel;
	private JButton dealButton, saveButton, leaveButton, helpButton,
						user1, user2, user3, user4,
						ai11, ai12, ai13, ai14,
						ai21, ai22, ai23, ai24,
						ai31, ai32, ai33, ai34,
						drawDeck, discardDeck,
						useCardButton, discardCardButton, replaceCardButton,
						spaceHolderButton;
	private JLabel userTitle, aiTitle1, aiTitle2, aiTitle3,
						selectedCardDraw, selectedCardDiscard,
						playerScoreLabel, parameterLabel,
						gameMessages;
	private String message = "Press 'DEAL' to begin game!";
	private ImageIcon drawDeckImage, discardDeckImage, 
						selectedCardDrawImage, selectedCardDiscardImage, 
						user1Image, user2Image, user3Image, user4Image, 
						ai11Image, ai12Image, ai13Image, ai14Image, 
						ai21Image, ai22Image, ai23Image, ai24Image, 
						ai31Image, ai32Image, ai33Image, ai34Image;
	private Timer outerPeekTimer;
	private Boolean actionActive = false;
	private PileName targetCard;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 1080;
	private final int WINDOW_LENGTH = 935;

	public Gameboard() {

		setTitle("Rat-A-Tat Cat");

		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		createImageIcons();
		createCardSlots();
		createButtons();
		createLabels();
		buildLayout();
		createTimers();

		add(panel);

		setVisible(true);
	} // End Gameboard constructor

	public void createButtons() {

		// Button for starting the game.
		dealButton = new JButton("DEAL!");
		dealButton.addActionListener(new ButtonListener());

		// Button for saving the game instantly 
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(new ButtonListener());

		// Button for leaving the game back to the menu
		leaveButton = new JButton("Leave Game");
		leaveButton.addActionListener(new ButtonListener());

		// Button for instant game info
		helpButton = new JButton("Help");
		helpButton.addActionListener(new ButtonListener());


		// Button for power card
		useCardButton = new JButton("Use");
		useCardButton.addActionListener(new PowerButtonListener());
		useCardButton.setVisible(false);

		// Button for power card
		discardCardButton = new JButton("Discard");
		discardCardButton.addActionListener(new PowerButtonListener());
		discardCardButton.setVisible(false);

		// Button for power card
		replaceCardButton = new JButton("Replace");
		replaceCardButton.addActionListener(new PowerButtonListener());
		replaceCardButton.setVisible(false);

		// This button is a spaceholder for the layout of the board when the buttons are hidden
		spaceHolderButton = new JButton("             ");
		spaceHolderButton.setOpaque(false);
		spaceHolderButton.setContentAreaFilled(false);
		spaceHolderButton.setBorderPainted(false);
	} // End createButtons

	public void createLabels() {

		userTitle = new JLabel("USERNAME");
		aiTitle1 = new JLabel("CPU 1");
		aiTitle2 = new JLabel("CPU 2");
		aiTitle3 = new JLabel("CPU 3");

		playerScoreLabel = new JLabel("PLAYER SCORE: poop");
		parameterLabel = new JLabel("Round 23");

		gameMessages = new JLabel(message);
	} // End createLabels

	public void createImageIcons() {
		drawDeckImage = new ImageIcon("images/backDeck.png");
		discardDeckImage = new ImageIcon("images/backEmpty.png");
		selectedCardDrawImage = new ImageIcon("images/backEmpty.png");
		selectedCardDiscardImage = new ImageIcon("images/backEmpty.png");
		// User's cards
		user1Image = new ImageIcon("images/backEmpty.png");
		user2Image = new ImageIcon("images/backEmpty.png");
		user3Image = new ImageIcon("images/backEmpty.png");
		user4Image = new ImageIcon("images/backEmpty.png");
		// CPU 1's cards
		ai11Image = new ImageIcon("images/backEmpty.png");
		ai12Image = new ImageIcon("images/backEmpty.png");
		ai13Image = new ImageIcon("images/backEmpty.png");
		ai14Image = new ImageIcon("images/backEmpty.png");
		// CPU 2's cards
		ai21Image = new ImageIcon("images/backSideEmpty.png");
		ai22Image = new ImageIcon("images/backSideEmpty.png");
		ai23Image = new ImageIcon("images/backSideEmpty.png");
		ai24Image = new ImageIcon("images/backSideEmpty.png");
		// CPU 3's cards
		ai31Image = new ImageIcon("images/backSideEmpty.png");
		ai32Image = new ImageIcon("images/backSideEmpty.png");
		ai33Image = new ImageIcon("images/backSideEmpty.png");
		ai34Image = new ImageIcon("images/backSideEmpty.png");
	}

	public void cardsFaceDown() {
		// User's cards
		user1Image = new ImageIcon("images/back.png");
		user2Image = new ImageIcon("images/back.png");
		user3Image = new ImageIcon("images/back.png");
		user4Image = new ImageIcon("images/back.png");
		// CPU 1's cards
		ai11Image = new ImageIcon("images/back.png");
		ai12Image = new ImageIcon("images/back.png");
		ai13Image = new ImageIcon("images/back.png");
		ai14Image = new ImageIcon("images/back.png");
		// CPU 2's cards
		ai21Image = new ImageIcon("images/backSide.png");
		ai22Image = new ImageIcon("images/backSide.png");
		ai23Image = new ImageIcon("images/backSide.png");
		ai24Image = new ImageIcon("images/backSide.png");
		// CPU 3's cards
		ai31Image = new ImageIcon("images/backSide.png");
		ai32Image = new ImageIcon("images/backSide.png");
		ai33Image = new ImageIcon("images/backSide.png");
		ai34Image = new ImageIcon("images/backSide.png");
			
		// actually making them face down
		user1.setIcon(user1Image);
		user2.setIcon(user2Image);
		user3.setIcon(user3Image);
		user4.setIcon(user4Image);
		ai11.setIcon(ai11Image);
		ai12.setIcon(ai12Image);
		ai13.setIcon(ai13Image);
		ai14.setIcon(ai14Image);
		ai21.setIcon(ai21Image);
		ai22.setIcon(ai22Image);
		ai23.setIcon(ai23Image);
		ai24.setIcon(ai24Image);
		ai31.setIcon(ai31Image);
		ai32.setIcon(ai32Image);
		ai33.setIcon(ai33Image);
		ai34.setIcon(ai34Image);
	} // end cardFaceDown

	public void createCardSlots() {

		// User's Cards
		user1 = new JButton(user1Image);
		user1.addActionListener(new CardButtonListener());
			user1.setOpaque(false);
			user1.setContentAreaFilled(false);
			user1.setBorderPainted(false);
		user2 = new JButton(user2Image);
		user2.addActionListener(new CardButtonListener());
			user2.setOpaque(false);
			user2.setContentAreaFilled(false);
			user2.setBorderPainted(false);
		user3 = new JButton(user3Image);
		user3.addActionListener(new CardButtonListener());
			user3.setOpaque(false);
			user3.setContentAreaFilled(false);
			user3.setBorderPainted(false);
		user4 = new JButton(user4Image);
		user4.addActionListener(new CardButtonListener());
			user4.setOpaque(false);
			user4.setContentAreaFilled(false);
			user4.setBorderPainted(false);

		// CPU 1's cards
		ai11 = new JButton(ai11Image);
		ai11.addActionListener(new CardButtonListener());
			ai11.setOpaque(false);
			ai11.setContentAreaFilled(false);
			ai11.setBorderPainted(false);
		ai12 = new JButton(ai12Image);
		ai12.addActionListener(new CardButtonListener());
			ai12.setOpaque(false);
			ai12.setContentAreaFilled(false);
			ai12.setBorderPainted(false);
		ai13 = new JButton(ai13Image);
		ai13.addActionListener(new CardButtonListener());
			ai13.setOpaque(false);
			ai13.setContentAreaFilled(false);
			ai13.setBorderPainted(false);
		ai14 = new JButton(ai14Image);
		ai14.addActionListener(new CardButtonListener());
			ai14.setOpaque(false);
			ai14.setContentAreaFilled(false);
			ai14.setBorderPainted(false);

		// CPU 2's cards
		ai21 = new JButton(ai21Image);
		ai21.addActionListener(new CardButtonListener());
			ai21.setOpaque(false);
			ai21.setContentAreaFilled(false);
			ai21.setBorderPainted(false);
		ai22 = new JButton(ai22Image);
		ai22.addActionListener(new CardButtonListener());
			ai22.setOpaque(false);
			ai22.setContentAreaFilled(false);
			ai22.setBorderPainted(false);
		ai23 = new JButton(ai23Image);
		ai23.addActionListener(new CardButtonListener());
			ai23.setOpaque(false);
			ai23.setContentAreaFilled(false);
			ai23.setBorderPainted(false);
		ai24 = new JButton(ai24Image);
		ai24.addActionListener(new CardButtonListener());
			ai24.setOpaque(false);
			ai24.setContentAreaFilled(false);
			ai24.setBorderPainted(false);

		// CPU 3's cards
		ai31 = new JButton(ai31Image);
		ai31.addActionListener(new CardButtonListener());
			ai31.setOpaque(false);
			ai31.setContentAreaFilled(false);
			ai31.setBorderPainted(false);
		ai32 = new JButton(ai32Image);
		ai32.addActionListener(new CardButtonListener());
			ai32.setOpaque(false);
			ai32.setContentAreaFilled(false);
			ai32.setBorderPainted(false);
		ai33 = new JButton(ai33Image);
		ai33.addActionListener(new CardButtonListener());
			ai33.setOpaque(false);
			ai33.setContentAreaFilled(false);
			ai33.setBorderPainted(false);
		ai34 = new JButton(ai34Image);
		ai34.addActionListener(new CardButtonListener());
			ai34.setOpaque(false);
			ai34.setContentAreaFilled(false);
			ai34.setBorderPainted(false);

		// Selected cards
		selectedCardDraw = new JLabel(selectedCardDrawImage);
		selectedCardDiscard= new JLabel(selectedCardDiscardImage);

		// Deck cards
		drawDeck = new JButton(drawDeckImage);
		drawDeck.addActionListener(new CardButtonListener());
			drawDeck.setOpaque(false);
			drawDeck.setContentAreaFilled(false);
			drawDeck.setBorderPainted(false);
		discardDeck = new JButton(discardDeckImage);
		discardDeck.addActionListener(new CardButtonListener());
			discardDeck.setOpaque(false);
			discardDeck.setContentAreaFilled(false);
			discardDeck.setBorderPainted(false);
	} // End createCardSlots

	public void createTimers() {

		outerPeekTimer = new Timer(5000, new TimerListener());

	}

	public void buildLayout() {

		// CREATE CENTER CENTER PANEL
		// This goes inside of the center panel -- for buttons looking nice
		centerCenterPanel = new JPanel();
		GroupLayout centerCenterLayout = new GroupLayout(centerCenterPanel);
		centerCenterPanel.setLayout(centerCenterLayout);
		centerCenterLayout.setAutoCreateGaps(true);
		centerCenterLayout.setAutoCreateContainerGaps(true);

		centerCenterLayout.setHorizontalGroup(
			centerCenterLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(dealButton)
				.addComponent(useCardButton)
				.addComponent(discardCardButton)
				.addComponent(replaceCardButton)
				.addComponent(spaceHolderButton)
		);

		centerCenterLayout.setVerticalGroup(
			centerCenterLayout.createSequentialGroup()
				.addComponent(dealButton)
				.addComponent(useCardButton)
				.addComponent(discardCardButton)
				.addComponent(replaceCardButton)
				.addComponent(spaceHolderButton)
		); // End center center panel layout

		// CREATE CENTER PANEL
		// This goes inside the bigger group layout below
		centerPanel = new JPanel();
		GroupLayout centerLayout = new GroupLayout(centerPanel);
		centerPanel.setLayout(centerLayout);
		centerLayout.setAutoCreateGaps(true);
		centerLayout.setAutoCreateContainerGaps(true);

		centerLayout.setHorizontalGroup(
			centerLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(gameMessages)
				.addGroup(centerLayout.createSequentialGroup()
					.addComponent(drawDeck)
					.addGap(81,81,81)
					.addComponent(discardDeck)
				)
				.addGroup(centerLayout.createSequentialGroup()
					.addComponent(selectedCardDraw)
					.addGap(60,60,60)
					.addComponent(centerCenterPanel)
					.addGap(60,60,60)
					.addComponent(selectedCardDiscard)
				)
		);

		centerLayout.setVerticalGroup(
			centerLayout.createSequentialGroup()
			.addGap(40,40,40)
			.addComponent(gameMessages)
			.addGap(30,30,30)
			.addGroup(centerLayout.createParallelGroup()
					.addComponent(drawDeck)
					.addComponent(discardDeck)
				)
			.addGroup(centerLayout.createParallelGroup()
				.addComponent(selectedCardDraw)
				.addComponent(centerCenterPanel)
				.addComponent(selectedCardDiscard)
			)
		); // End center panel layout
		
		// CREATE TOP LEFT BUTTON LAYOUT
		// This goes in the bigger group layout below
		topLeftPanel = new JPanel();
		GroupLayout topLeftLayout = new GroupLayout(topLeftPanel);
		topLeftPanel.setLayout(topLeftLayout);
		topLeftLayout.setAutoCreateGaps(true);
		topLeftLayout.setAutoCreateContainerGaps(true);

		topLeftLayout.setHorizontalGroup(
			topLeftLayout.createParallelGroup()
				.addComponent(saveButton)
				.addComponent(leaveButton)
		);

		topLeftLayout.setVerticalGroup(
			topLeftLayout.createSequentialGroup()
				.addGap(20,20,20)
				.addComponent(saveButton)
				.addComponent(leaveButton)
		); // end top left 

		// CREATE BOTTOM RIGHT INFO LAYOUT
		// This goes in the bigger group layout below
		bottomRightPanel = new JPanel();
		GroupLayout buttonRightLayout = new GroupLayout(bottomRightPanel);
		bottomRightPanel.setLayout(buttonRightLayout);
		buttonRightLayout.setAutoCreateGaps(true);
		buttonRightLayout.setAutoCreateContainerGaps(true);

		buttonRightLayout.setHorizontalGroup(
			buttonRightLayout.createParallelGroup()
				.addComponent(playerScoreLabel)
				.addComponent(parameterLabel)
		);

		buttonRightLayout.setVerticalGroup(
			buttonRightLayout.createSequentialGroup()
				.addGap(60,60,60)
				.addComponent(playerScoreLabel)
				.addComponent(parameterLabel)
		); // end botton right

		//////////////////// THE MAIN LAYOUT ////////////////////
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(20,20,20)
					.addComponent(topLeftPanel)
					.addGap(130,130,130)
					.addComponent(ai11)
					.addComponent(ai12)
					.addComponent(ai13)
					.addComponent(ai14)
					.addGap(190,190,190)
					.addComponent(helpButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(60,60,60)
					.addComponent(aiTitle2)
					.addGap(418,418,418)
					.addComponent(aiTitle1)
					.addGap(420,420,420)
					.addComponent(aiTitle3)
				)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addComponent(ai21)
						.addComponent(ai22)
						.addComponent(ai23)
						.addComponent(ai24)
					)

					.addGap(150,150,150)
					.addComponent(centerPanel)
					.addGap(47,47,47)

					.addGap(90,90,90)
					.addGroup(layout.createParallelGroup()
						.addComponent(ai31)
						.addComponent(ai32)
						.addComponent(ai33)
						.addComponent(ai34)
					)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(505,505,505)
					.addComponent(userTitle)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(283,283,283)
					.addComponent(user1)
					.addComponent(user2)
					.addComponent(user3)
					.addComponent(user4)
					.addGap(50,50,50)
					.addComponent(bottomRightPanel)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(20,20,20)
				.addGroup(layout.createParallelGroup()
					.addComponent(topLeftPanel)
					.addComponent(ai11)
					.addComponent(ai12)
					.addComponent(ai13)
					.addComponent(ai14)
					.addComponent(helpButton)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(aiTitle2)
					.addComponent(aiTitle1)
					.addComponent(aiTitle3)
				)
				.addGroup(layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addComponent(ai21)
						.addComponent(ai22)
						.addComponent(ai23)
						.addComponent(ai24)
					)
					
					.addComponent(centerPanel)
					
					.addGroup(layout.createSequentialGroup()
						.addComponent(ai31)
						.addComponent(ai32)
						.addComponent(ai33)
						.addComponent(ai34)
					)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(userTitle)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(user1)
					.addComponent(user2)
					.addComponent(user3)
					.addComponent(user4)
					.addComponent(bottomRightPanel)
				)
		); // End main layout
	} // End buildLayout

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == dealButton) {
				// Set actionActive to true
				actionActive = true;

				// send to wrapper to shuffle and distribute the cards
				// ONCE THE DECK IS DEALT, you need to hide the deal button until the next round...
				gameMessages.setText("Peek at your outer two cards. You only have 5 seconds!");
				dealButton.setVisible(false);
				cardsFaceDown();

				// Reveal the top of the discard
				String topOfDeckString = "images/"+gameState.getFaceUpDiscard().toString()+".png";
				discardDeckImage = new ImageIcon(topOfDeckString);
				discardDeck.setIcon(discardDeckImage);

				// Revealing outer two
				HashMap<String, CardName[]> playerHands = gameState.getPlayerHands();

				CardName x1 = playerHands.get(players[0])[0];
				CardName x4 = playerHands.get(players[0])[3];

				//Integer i = new Integer();
				String poop = Integer.toString(CardName.getNumericScore(x1));
				String poopie = Integer.toString(CardName.getNumericScore(x4));
 
				String user1ImageString = "images/"+poop+".png";
				String user4ImageString = "images/"+poopie+".png";
				user1Image = new ImageIcon(user1ImageString); ///////////////////////////
				user4Image = new ImageIcon(user4ImageString); ///////////////////////////
				user1.setIcon(user1Image);
				user4.setIcon(user4Image);			

				// INITIATE TIMER for PEEKING OUTER TWO
				outerPeekTimer.start();
			}
			else if (e.getSource() == saveButton) {
				// save the current state of the game -- what is needed for the current state?
				// current hands, parameters, scores, -- can only save on users' turn since AI instant
				//gameWrapper.saveGame(username, gamename);
				JOptionPane.showMessageDialog(null, "Game has been saved", "Saved Game", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (e.getSource() == leaveButton) {
				// prompt an "are you sure"
				// if yes, prompt a "do you want to save the game?"
				// save current state of game, otherwise scrap the whole game...?
				// or force save all?
				int reply = JOptionPane.showConfirmDialog(null, "Would you like to save the game first?");

				// Then go back to the menu
				if (reply == JOptionPane.YES_OPTION) {
					//gameWrapper.saveGame(username, gamename)
					new MenuWindow();
					setVisible(false);
				}
				else if (reply == JOptionPane.NO_OPTION) {
					new MenuWindow();
					setVisible(false);
				}
			}
			else if (e.getSource() == helpButton) {
					try {
					// instantiate the help class window
					new HelpWindow();
					} catch (FileNotFoundException f) {

					}
			}
		} // end actionPerformed
	}

	private class CardButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == drawDeck) {
				if (actionActive == false) {
					actionActive = true;
					// Display instructions for what to do after selecting from discard
					gameMessages.setText("Now choose a card from your hand to replace it with.");

					// Display the selected card from top of the deck in the selectedCardDraw slot.
					int c = CardName.getNumericScore(gameState.getTopOfDeck());
					String topOfDeckString = "images/"+Integer.toString(c)+".png";
					drawDeckImage = new ImageIcon(topOfDeckString);
					selectedCardDraw.setIcon(drawDeckImage);

					if (CardName.getNumericScore(gameState.getTopOfDeck()) != 0 && gameState.getTopOfDeck() != CardName.ZERO) {
						// If that card is a regular card, display buttons for what to do with it
						// Replace & Discard
						replaceCardButton.setVisible(true);
						discardCardButton.setVisible(true);
					}
					else {
						// If that card is a power card, display buttons for what to do with it
						// Display the power card buttons
						replaceCardButton.setVisible(true);
						discardCardButton.setVisible(true);
						useCardButton.setVisible(true);
					}
				}
				else if (actionActive == true) {
					gameMessages.setText("ERROR with drawDeck drawing.");
				}
				else {
					// ERRORS
				}
			}
			else if (e.getSource() == discardDeck) {
				if (actionActive == false) {
					actionActive = true;
					// Display the selected card from the top of the deck in the selectedCardDiscard.
					// String topOfDeckString = "images/"+gameState.getFaceUpDiscard().toString()+".png";			
					// discardDeckImage = new ImageIcon(topOfDeckString);
					// selectedCardDiscard.setIcon(new ImageIcon(discardDeckImage));
					selectedCardDiscard.setIcon(discardDeckImage);

					// Display instructions for what to do after selecting from discard
					gameMessages.setText("Now choose a card from your hand to replace it with.");

					// user then selects card from hand
					//selectedCard = 
				}
				else if (actionActive == true) {
					gameMessages.setText("ERROR with discardDeck drawing.");
				}
				else {
					//Errors
				}
			}
			else if (e.getSource() == user1) {
				// If replacing draw or discard is active
					targetCard = PileName.ZERO;

				// If SWAP is active
					// If AIselectedCard is not null
						// store card into USERselectedCard
						// SEND TO GAMEWRAPPER

				// If PEEK is active
					// allow user to view it ... 
					// timer shit again for setting back to back up
					// set to face up
			}
			else if (e.getSource() == user2) {
				// If replacing draw or discard is active
					targetCard = PileName.ONE;

				// If SWAP is active
					// If AIselectedCard is not null
						// store card into USERselectedCard
						// SEND TO GAMEWRAPPER
				// If PEEK is active
					// allow user to view it ... 
					// timer shit again for setting back to back up
					// set to face up
			}
			else if (e.getSource() == user3) {
				// If replacing draw or discard is active
					targetCard = PileName.TWO;

				// If SWAP is active
					// If AIselectedCard is not null
						// store card into USERselectedCard
						// SEND TO GAMEWRAPPER
				// If PEEK is active
					// allow user to view it ... 
					// timer shit again for setting back to back up
					// set to face up
			}
			else if (e.getSource() == user4) {
				// If replacing draw or discard is active
					targetCard = PileName.THREE;

				// If SWAP is active
					// If AIselectedCard is not null
						// store card into USERselectedCard
						// SEND TO GAMEWRAPPER
				// If PEEK is active
					// allow user to view it ... 
					// timer shit again for setting back to back up
					// set to face up

			}
			else if (e.getSource() == ai11) {
				// If replacing draw or discard is active
					targetCard = PileName.ZERO;

				// If SWAP is active
					// allow user to select this card, store into a AIselectedCard variable
				// If SWAP is inactive
					// POPUP ERROR SAYING NOT OK
			}
			else if (e.getSource() == ai12) {
				// If replacing draw or discard is active
					targetCard = PileName.ONE;

			}
			else if (e.getSource() == ai13) {
				// If replacing draw or discard is active
					targetCard = PileName.TWO;

			}
			else if (e.getSource() == ai14) {
				// If replacing draw or discard is active
					targetCard = PileName.THREE;

			}
			else if (e.getSource() == ai21) {
				// If replacing draw or discard is active
					targetCard = PileName.ZERO;

			}
			else if (e.getSource() == ai22) {
				// If replacing draw or discard is active
					targetCard = PileName.ONE;

			}
			else if (e.getSource() == ai23) {
				// If replacing draw or discard is active
					targetCard = PileName.TWO;

			}
			else if (e.getSource() == ai24) {
				// If replacing draw or discard is active
					targetCard = PileName.THREE;

			}
			else if (e.getSource() == ai31) {
				// If replacing draw or discard is active
					targetCard = PileName.ZERO;

			}
			else if (e.getSource() == ai32) {
				// If replacing draw or discard is active
					targetCard = PileName.ONE;

			}
			else if (e.getSource() == ai33) {
				// If replacing draw or discard is active
					targetCard = PileName.TWO;

			}
			else if (e.getSource() == ai34) {
				// If replacing draw or discard is active
					targetCard = PileName.THREE;

			}
		} // End actionPerformed
	} // End CardButtonListener

	public class PowerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == useCardButton) {
				// This button should only show up and be pressed if the card when drawn is identified to be a power card
				// PERFORM THE POWER CARD ACTIONS HERE
			}
			else if (e.getSource() == discardCardButton) {
				// Send the card to the discard pile
				Action a = new Action(ActionName.DRAW, PileName.DECK, PileName.DISCARD, players[0], players[0], null);
			}
			else if (e.getSource() == replaceCardButton) {
				// Send card to the player's hand -- replacing the card.
				gameMessages.setText("Select a target card");
				Action a = new Action(ActionName.DRAW, PileName.DECK, targetCard, players[0], players[0], null);

			}
		} // End actionPerformed
	} // End PowerButtonListener

	/**
	 * This class is used to set the cards to "normal" faced down after 5 seconds of being activated
	 * PROBLEM -- timer is activated upon any button click...
	 */
	public class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Set the outer cards to face up
			user1Image = new ImageIcon("images/back.png");
			user4Image = new ImageIcon("images/back.png");
			user1.setIcon(user1Image);
			user4.setIcon(user4Image);

			// Change message
			gameMessages.setText("Now choose from the either of the decks.");

			// Set actionActive to false
			actionActive = false;

		} // End actionPerformed
	} // End TimeListener
} // End Gameboard
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}