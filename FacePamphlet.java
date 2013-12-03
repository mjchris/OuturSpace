/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {
	
/* Instance variable for FacePamphletProfile profile and currentProfile */
	private FacePamphletProfile profile;
	private FacePamphletProfile currentProfile;
	
/* Instance variable for FacePamphletDatabase database */
	private FacePamphletDatabase database = new FacePamphletDatabase();
	
/* Instance variable for FacePamphletCanvas canvas */
	private FacePamphletCanvas canvas = new FacePamphletCanvas();

/* Instance variable for JTextFields */
	private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField statusField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField pictureField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField friendField = new JTextField(TEXT_FIELD_SIZE);

/* Constructor: FacePamphlet() */
	public FacePamphlet() {
		add(canvas);
	}

/* Method: init() */
/**
 * This method has the responsibility for initializing the 
 * interactors in the application, and taking care of any other 
 * initialization that needs to be performed.
 */
	public void init() {
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		add(new JButton("Remove Friend"), WEST);
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		friendField.addActionListener(this);
		addActionListeners();
    }
    
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked or interactors are used.
 */
    public void actionPerformed(ActionEvent e) {
    	String msg;
		if(e.getActionCommand() == "Add" && !nameField.getText().isEmpty()) {
			if(!database.containsProfile(nameField.getText())) {
				profile = new FacePamphletProfile(nameField.getText());
				database.addProfile(profile);
				msg = "New profile created";
			} else {
				profile = database.getProfile(nameField.getText());
				msg = "A profile with the name " + nameField.getText() + " already exists";
			}
			currentProfile = profile;
			canvas.displayProfile(currentProfile);
			canvas.showMessage(msg);
			nameField.setText("");
		}
		if(e.getActionCommand() == "Delete" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				database.deleteProfile(nameField.getText());
				msg = "Profile of " + nameField.getText() + " deleted";
			} else {
				msg = "A profile with the name " + nameField.getText() + " does not exist";
			}
			currentProfile = null;
			canvas.removeAll();
			canvas.showMessage(msg);
			nameField.setText("");
		}
		if(e.getActionCommand() == "Lookup" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				profile = database.getProfile(nameField.getText());
				msg = "Displaying " + nameField.getText();
			} else {
				profile = null;
				msg = "A profile with the name " + nameField.getText() + " does not exist";
			}
			currentProfile = profile;
			if(currentProfile == null) {
				canvas.removeAll();
			} else {
				canvas.displayProfile(currentProfile);
			}
			canvas.showMessage(msg);
			nameField.setText("");
		}
		if((e.getSource().equals(statusField) || e.getActionCommand() == "Change Status") && !statusField.getText().isEmpty()) {
			if(currentProfile != null) {
				currentProfile.setStatus(statusField.getText());
				database.addProfile(currentProfile);
				msg = "Status updated to " + statusField.getText();
			} else {
				msg = "Please select a profile to change status";
			}
			if(currentProfile == null) {
				canvas.removeAll();
			} else {
				canvas.displayProfile(currentProfile);
			}
			canvas.showMessage(msg);
			statusField.setText("");
		}
		if((e.getSource().equals(pictureField) || e.getActionCommand() == "Change Picture") && !pictureField.getText().isEmpty()) {
			if(currentProfile != null) {
				try {
					GImage image = new GImage(pictureField.getText());
					currentProfile.setImage(image);
					database.addProfile(currentProfile);
					msg = "Picture updated";
				} catch(ErrorException ex) {
					msg = "Unable to open image file: " + pictureField.getText();
				}
			} else {
				msg = "Please select a profile to change picture";
			}
			if(currentProfile == null) {
				canvas.removeAll();
			} else {
				canvas.displayProfile(currentProfile);
			}
			canvas.showMessage(msg);
			pictureField.setText("");
		}
		if(e.getActionCommand() == "Add Friend" && !friendField.getText().isEmpty()) {
			ArrayList<String> friendCheck = new ArrayList<String>();
			if(currentProfile != null) {
				if(database.containsProfile(friendField.getText())) {
					if(database.getProfile(friendField.getText()) != currentProfile) {
						Iterator<String> friendsIterator = currentProfile.getFriends();
						while(friendsIterator.hasNext()) {
							friendCheck.add(friendsIterator.next());
						}
						if(!friendCheck.contains(friendField.getText())) {
							currentProfile.addFriend(friendField.getText());
							database.addProfile(currentProfile);
							profile = database.getProfile(friendField.getText());
							profile.addFriend(currentProfile.getName());
							database.addProfile(profile);
							msg = friendField.getText() + " added as a friend";
						} else {
							msg = currentProfile.getName() + " already has " + 
						}	
					} else {
						println("Users cannot be friends with themselves.");
					}
				} else {
					println("That profile does not exist.");
				}
			} else {
				msg = "Please select a profile to add friend";
			}
			if(currentProfile == null) {
				canvas.removeAll();
			} else {
				canvas.displayProfile(currentProfile);
			}
			friendField.setText("");
		}
		if(e.getActionCommand() == "Remove Friend" && !friendField.getText().isEmpty()) {
			ArrayList<String> friendCheck = new ArrayList<String>();
			if(currentProfile != null) {
				if(database.containsProfile(friendField.getText())) {
					Iterator<String> friendsIterator = currentProfile.getFriends();
					while(friendsIterator.hasNext()) {
						friendCheck.add(friendsIterator.next());
					}
					if(friendCheck.contains(friendField.getText())) {
						currentProfile.removeFriend(friendField.getText());
						database.addProfile(currentProfile);
						profile = database.getProfile(friendField.getText());
						profile.removeFriend(currentProfile.getName());
						database.addProfile(profile);
						println("Remove Friend: " + friendField.getText() + ": " + currentProfile.toString());
					} else {
						println(currentProfile.getName() + " does not have that friend.");
					}
				} else {
					println("That profile does not exist.");
				}
			} else {
				println("Select a profile first.");
			}
			if(currentProfile == null) {
				canvas.removeAll();
			} else {
				canvas.displayProfile(currentProfile);
			}
			friendField.setText("");
		}
	}

}