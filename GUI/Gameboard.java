import javax.swing.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class Gameboard extends JFrame {

	// Fields
	private JPanel panel;

	// Constants -- WINDOW SIZE
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_LENGTH = 550;

	public Gameboard() {

		setTitle("Rat-A-Tat Cat");

		setSize(WINDOW_WIDTH, WINDOW_LENGTH);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel():



		add(panel);

		setVisible(true);

	}

	
	
	public static void main(String[] args) {
		new Gameboard();
	}
}