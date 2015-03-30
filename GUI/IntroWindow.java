import javax.swing.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class IntroWindow extends JFrame {

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;


	/**
	 * The default constructor builds splash screen window...
	 */
	public IntroWindow() {

		// Set window title
		setTitle("Intro Window");

		// Set window size
		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		// Specify an action for close "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add layout manager to content pane
		setLayout(new BorderLayout());

		// Create a panel
		JPanel logoPanel = new JPanel();

		// Create label that image will go in
		JLabel logoLabel = new JLabel();

		// Create image
		ImageIcon logo = new ImageIcon("logo.jpg");
		logoLabel.setIcon(logo);
		//introLabel.setText(null);

		// set label dimension -- WANT THE WHOLE GAME TO BE THIS SIZE CONSISTANTLY
		//introLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_LENGTH));

		// Create a label
		logoPanel.add(logoLabel);

		//add(introLabel, BorderLayout.CENTER);
		add(logoPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		new IntroWindow();
	}
}