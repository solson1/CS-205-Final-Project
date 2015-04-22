import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

/**
 * @author lilynguyen
 * INSTRUCTIONS WINDOW 												 
 * The first window to pop up after the splash screen. This window 
 * displays the game information.
 *
 * The main GUI class will call this by just create a new object.
 *
 * NOTES: 
 * Do you want it to just be the general blurb about the game, or the
 * full instructions?
 */

// java -splash:images/splash.png InstructionWindow

public class InstructionWindow extends JFrame {

	// Fields
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
	}

	/**
	 * This method creates a label object with an Icon object as its contents.
	 */

	public void createLogo() {
		logoLabel = new JLabel(new ImageIcon("images/logo.png"));
	}

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
	}

	/**
	 * This method creates a button to move on to the menu after user is done reading.
	 * This button makes the program go to the MENU window next.
	 */

	public void createNextButton() {
		nextButton = new JButton("Next");
		nextButton.addActionListener(new NextButtonListener());
	}

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
		}
	}

	/*
	* main exists solely for testing individual class window functionality.
	*/

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Thread.sleep(4000);
		new InstructionWindow();
	}
}