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
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram implements FacePamphletConstants {
	
/* Instance variable for FacePamphletProfile profile */
	public FacePamphletProfile profile;
	
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
				println("Add: profile for " + nameField.getText() + " already exists: " + profile.toString());
			}
			nameField.setText("");
		}
		if(e.getActionCommand() == "Delete" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				database.deleteProfile(nameField.getText());
				println("Delete: " + nameField.getText());
			} else {
				println("Delete: profile for " + nameField.getText() + " does not exist.");
			}
			nameField.setText("");
		}
		if(e.getActionCommand() == "Lookup" && !nameField.getText().isEmpty()) {
			if(database.containsProfile(nameField.getText())) {
				profile = database.getProfile(nameField.getText());
				println("Lookup: " + profile.toString());
			} else {
				println("That profile does not exist.");
			}
			nameField.setText("");
		}
		if((e.getSource().equals(statusField) || e.getActionCommand() == "Change Status") && !statusField.getText().isEmpty()) {
			profile.setStatus(statusField.getText());
			println(profile.toString());
			statusField.setText("");
		}
		if((e.getSource().equals(pictureField) || e.getActionCommand() == "Change Picture") && !pictureField.getText().isEmpty()) {
			GImage image = new GImage(pictureField.getText());
			profile.setImage(image);
			pictureField.setText("");
		}
		if((e.getSource().equals(friendField) || e.getActionCommand() == "Add Friend") && !friendField.getText().isEmpty()) {
			profile.addFriend(friendField.getText());
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