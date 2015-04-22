import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.*;

/**
 * @author lilynguyen
 * style choice -- no need for imageicon instances since we can only update jbutton labels with setIcon.
*/

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
	private Timer timer;

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
		//selectedCardDraw.setVisible(false);
		selectedCardDiscard= new JLabel(selectedCardDiscardImage);
		//selectedCardDiscard.setVisible(false);

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
				// send to wrapper to shuffle and distribute the cards
				// ONCE THE DECK IS DEALT, you need to hide the deal button until the next round...
				gameMessages.setText("Peek at your outer two cards. You only have 5 seconds!");
				dealButton.setVisible(false);
				cardsFaceDown();

				// INITIATE TIMER for PEEKING OUTER TWO
				timer = new Timer(5000, new TimerListener());
				timer.start();

				// Revealing outer two
				user1Image = new ImageIcon("images/1.png"); ///////////////////////////
				user4Image = new ImageIcon("images/1.png"); ///////////////////////////
				user1.setIcon(user1Image);
				user4.setIcon(user4Image);

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
				// Display the selected card from top of the deck in the selectedCardDraw slot.

				// If that card is a regular card
					// Replace
					// Discard
				// If that card is a power card
					// Display the power card buttons
			}
			else if (e.getSource() == discardDeck) {
				// Display the selected card from the top of the deck in the selectedCardDiscard.
					// user then selects card from hand
			}
			else if (e.getSource() == user1) {
				// If replacing draw or discard is active
					//
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
				// If SWAP is active
					// allow user to select this card, store into a AIselectedCard variable
				// If SWAP is inactive
					// POPUP ERROR SAYING NOT OK
			}
			else if (e.getSource() == ai12) {

			}
			else if (e.getSource() == ai13) {

			}
			else if (e.getSource() == ai14) {

			}
			else if (e.getSource() == ai21) {

			}
			else if (e.getSource() == ai22) {

			}
			else if (e.getSource() == ai23) {

			}
			else if (e.getSource() == ai24) {

			}
			else if (e.getSource() == ai31) {

			}
			else if (e.getSource() == ai32) {

			}
			else if (e.getSource() == ai33) {

			}
			else if (e.getSource() == ai34) {

			}
		} // End actionPerformed
	} // End CardButtonListener

	public class PowerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

		} // End actionPerformed
	} // End PowerButtonListener

	public class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Set the outer cards to face up
			user1Image = new ImageIcon("images/back.png");
			user4Image = new ImageIcon("images/back.png");
			user1.setIcon(user1Image);
			user4.setIcon(user4Image);

			// Change message
			gameMessages.setText("Now choose from the either of the decks.");

			// Take card from top of drawDeck for the first discardDeck card

			// Reveal the top of the discard
//			discardDeckImage = new ImageIcon("images"+gameState.faceUpDiscard.getNumericScore.toString()+".png");
//			discardDeck.setIcon(discardDeckImage);			

		} // End actionPerformed
	} // End TimeListener

	public static void main(String[] args) {
		new Gameboard();
	}
	/**
	actions are going to be object of the action class that gets sent to the wrapper
	YOU'RE CREATING THE ACTION OBJECTS! LOOK INTO THAT FOR EACH BUTTON...

	Enum Pilename.Deck is the data type for the deck

	action name is the power card which is also an enum

	back from the gamewrapper you're getting game states and get the fields from that accordingly
	*/
}