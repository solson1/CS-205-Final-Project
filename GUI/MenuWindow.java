import javax.swing.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class MenuWindow extends JFrame {

	// Fields
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
		playersButton = new JButton("Change Player");
		playersButton.addActionListener(new PlayersButtonListener());

		playButton = new JButton("PLAY GAME!");
		playButton.addActionListener(new PlayButtonListener());

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
	}


	/**
	 *
	 */
	public void createNameLabel() {
		nameLabel = new JLabel("Welcome USERNAME");
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
	}

	/**
	 * This ActionListener goes to the player window page.
	 */

	private class PlayersButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new PlayerListWindow();
			setVisible(false);
		}
	}

	/**
	 * This ActionListener begins the game process.
	 */

	private class PlayButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ParameterWindow();
			setVisible(false);
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