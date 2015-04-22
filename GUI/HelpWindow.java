import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

/**
 * @author lilynguyen
 */

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new HelpWindow();
	}
}