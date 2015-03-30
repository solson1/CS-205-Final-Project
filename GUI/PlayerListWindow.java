import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class PlayerListWindow extends JFrame {

	// Fields
	private JPanel panel;
	private JList playerList;
	private JButton backButton, deleteButton, addButton, statsButton;

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
		buildLayout();
		
		// Add panel to frame
		add(panel);

		// Set to visible
		setVisible(true);
	}

	public void createPlayerList() {

		String[] players = {"NAME","NAME","NAME"};

		// Create list componenet with values from list in it
		playerList = new JList (players);

		// Selection selection mode
		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Register the list selection listener
		playerList.addListSelectionListener(new ListListener());
	}

	public void createButtons() {

		backButton = new JButton("Back");
		backButton.addActionListener(new ButtonListener());

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ButtonListener());

		addButton = new JButton("Add");
		addButton.addActionListener(new ButtonListener());

		statsButton = new JButton("Stats");
		statsButton.addActionListener(new ButtonListener());
	}

	public void buildLayout() {

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addComponent(playerList)
					)
					.addGroup(layout.createSequentialGroup()
						.addComponent(backButton)
						.addComponent(deleteButton)
						.addComponent(addButton)
						.addComponent(statsButton)
					)
		);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(playerList)
					.addGroup(layout.createParallelGroup()
						.addComponent(backButton)
						.addComponent(deleteButton)
						.addComponent(addButton)
						.addComponent(statsButton)
					)
		);

	}

	private class ListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			// Get selected name
			String selection = (String) playerList.getSelectedValue();

			// DO X WITH THE SELECTED NAME
		}
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == backButton) {
				// MAIN MENU
			}
			else if (e.getSource() == deleteButton) {
				// DELETE SELECTED USERNAME
			}
			else if (e.getSource() == addButton) {
				// ADD NEW USER
			}
			else if (e.getSource() == statsButton) {
				// VIEW STATISTICS OF USER
			}
		}
	}

	public static void main(String[] args) {
		new PlayerListWindow();
	}
}