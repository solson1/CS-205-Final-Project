import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * @author lilynguyen
*/

public class PlayerListWindow extends JFrame {

	// Fields
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
	}

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

	public void createPlayerLogo() {
		playerLogoLabel = new JLabel(new ImageIcon("images/playerlist.png"));
	} // End createPlayerLogo

	public void nameSelectError() {
		String message = "You must select a name from the list.";
		String title = "Error";
		ImageIcon icon = new ImageIcon("images/error.png");

		// Prompt a pop up asking for user to select a username.
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, icon);
	} // End nameSelectError

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

	private class ListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			// Get selected name
			nameSelection = (String) playerList.getSelectedValue();
		} // End valueChanged
	} // End ListListener class

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
				usernameNew = JOptionPane.showInputDialog("Please enter a username:");

				// ADD NEW USER
				//gameWrapper.createProfile(username);
			}
			else if (e.getSource() == statsButton) {
				if (nameSelection == null) {
					// if no name is selected, error occurs
					nameSelectError();
				}
				else {
					// VIEW STATISTICS OF USER
					// Need to read input of the value highlighted
					// IN REGARDS TO VARIABLE selection, LOAD STATS WINDOW
				}
			}
		} // End actionPerformed
	} // End ButtonListener class

	public static void main(String[] args) {
		new PlayerListWindow();
	}
}