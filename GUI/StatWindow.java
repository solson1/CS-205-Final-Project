import javax.swing.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class StatWindow extends JFrame {
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

		createContent();
		buildLayout();

		// Add panel to frame
		add(panel);

		// Set to visible.
		setVisible(true);

	}

	public void createContent() {
		
	}

	public void buildLayout() {

	}

	public static void main(String[] args) {
		new StatWindow();
	}
}