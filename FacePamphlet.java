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
import java.util.ArrayList;

import javax.swing.*;

public class FacePamphlet extends ConsoleProgram implements FacePamphletConstants {
	
/* Instance variable for FacePamphletProfile profile and currentProfile */
	public FacePamphletProfile profile;
	public FacePamphletProfile currentProfile;
	
/* Instance variable for FacePamphletDatabase database */
	public FacePamphletDatabase database = new FacePamphletDatabase();

/* Instance variable for JTextFields */
	private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField statusField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField pictureField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField friendField = new JTextField(TEXT_FIELD_SIZE);

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
		if(e.getActionCommand() == "Add" && !nameField.getText().isEmpty()) {
			if(!database.containsProfile(nameField.getText())) {
				profile = new FacePamphletProfile(nameField.getText());
				database.addProfile(profile);
				println("Add: new profile: " + profile.toString());
			} else {
				profile = database.getProfile(nameField.getText());
				println("Add: profile for " + nameField.getText() + " already exists: " + profile.toString());
			}
			currentProfile = profile;
			nameField.setText("");
		}
		if(e.getActionCommand() == "Delete" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				database.deleteProfile(nameField.getText());
				println("Delete: " + nameField.getText());
			} else {
				println("Delete: profile for " + nameField.getText() + " does not exist");
			}
			currentProfile = null;
			nameField.setText("");
		}
		if(e.getActionCommand() == "Lookup" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				profile = database.getProfile(nameField.getText());
				println("Lookup: " + profile.toString());
			} else {
				profile = null;
				println("Lookup: profile for " + nameField.getText() + " does not exist");
			}
			currentProfile = profile;
			nameField.setText("");
		}
		if((e.getSource().equals(statusField) || e.getActionCommand() == "Change Status") && !statusField.getText().isEmpty()) {
			if(currentProfile != null) {
				currentProfile.setStatus(statusField.getText());
				database.addProfile(currentProfile);
				println("Change Status: " + currentProfile.toString());
			} else {
				println("Select a profile first.");
			}
			statusField.setText("");
		}
		if((e.getSource().equals(pictureField) || e.getActionCommand() == "Change Picture") && !pictureField.getText().isEmpty()) {
			if(currentProfile != null) {
				try {
					GImage image = new GImage(pictureField.getText());
					currentProfile.setImage(image);
					database.addProfile(currentProfile);
					println("Change Picture: " + pictureField.getText() + ": " + currentProfile.toString());
				} catch(ErrorException ex) {
					println("That picture does not exist.");
				}
			} else {
				println("Select a profile first.");
			}
			pictureField.setText("");
		}
		if((e.getSource().equals(friendField) || e.getActionCommand() == "Add Friend") && !friendField.getText().isEmpty()) {
			ArrayList<String> friendCheck = new ArrayList<String>();
			if(currentProfile != null) {
				if(database.containsProfile(friendField.getText())) {
					while(currentProfile.getFriends().hasNext()) {
						friendCheck.add(currentProfile.getFriends().next());
					}
					if(!friendCheck.contains(friendField.getText())) {
						currentProfile.addFriend(friendField.getText());
						database.addProfile(currentProfile);
						println("Add Friend: " + friendField.getText() + ": " + currentProfile.toString());
					}
				}
			} else {
				println("Select a profile first.");
			}
			profile.addFriend(friendFielsd.getText());
			println(profile.toString());
			friendField.setText("");
		}
		if(e.getActionCommand() == "Remove Friend" && !friendField.getText().isEmpty()) {
			profile.removeFriend(friendField.getText());
			println(profile.toString());
			friendField.setText("");
		}
	}

}