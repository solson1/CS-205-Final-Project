import javax.swing.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class MenuWindow extends JFrame {

	// Fields
	private JPanel panel;
	private JLabel logoLabel;
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
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set to visible
		setVisible(true);

	}

	/**
	 * This method creates a label object with an Icon object as its contents.
	 */

	public void createLogo() {
		logoLabel = new JLabel(new ImageIcon("images/logo.png"));
	}

	/**
	 * This method creates a button 
	 */

	public void createButtons() {
		playersButton = new JButton("Player List");
		playersButton.addActionListener(new PlayersButtonListener());

		playButton = new JButton("PLAY GAME!");
		playButton.addActionListener(new PlayButtonListener());

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
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
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(209,209,209)
					.addComponent(logoLabel)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(299,299,299)
					.addComponent(playersButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(295,295,295)
					.addComponent(playButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addGap(315,315,315)
					.addComponent(exitButton)
				)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(40,40,40)
				.addComponent(logoLabel)
				.addGap(60,60,60)
				.addComponent(playersButton)
				.addGap(40,40,40)
				.addComponent(playButton)
				.addGap(40,40,40)
				.addComponent(exitButton)
		);
	}

	/**
	 * This ActionListener goes to the player window page.
	 */

	private class PlayersButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// GO TO PLAYERS WINDOW
		}
	}

	/**
	 * This ActionListener begins the game process.
	 */

	private class PlayButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// GO TO PLAY WINDOW
		}
	}

	/**
	 * This ActionListener exits the program.
	 */

	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MenuWindow();
	}
}