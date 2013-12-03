/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

/* Constructor: FacePamphletCanvas() */
/** 
 * This method takes care of any initialization needed for 
 * the display.
 */
	public FacePamphletCanvas() {
		// You fill this in
	}

/* Method: showMessage(msg) */
/** 
 * This method displays a message string near the bottom of the 
 * canvas. Every time this method is called, the previously 
 * displayed message (if any) is replaced by the new message text 
 * passed in.
 */
	public void showMessage(String msg) {
		GLabel label = new GLabel(msg);
		label.setFont(MESSAGE_FONT);
		label.setLocation((getWidth() - label.getWidth())/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		add(label);
	}
	
/* Method displayProfile(profile) */
/** 
 * This method displays the given profile on the canvas. The 
 * canvas is first cleared of all existing items (including 
 * messages displayed near the bottom of the screen) and then the 
 * given profile is displayed. The profile display includes the 
 * name of the user from the profile, the corresponding image 
 * (or an indication that an image does not exist), the status of
 * the user, and a list of the user's friends in the social network.
 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		GLabel name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		name.setLocation(LEFT_MARGIN, TOP_MARGIN + name.getAscent());
		add(name);
		GImage image = profile.getImage();
		if(image != null) {
			image.setBounds(LEFT_MARGIN, name.getY() + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		} else {
			GRect imageFill = new GRect(LEFT_MARGIN, name.getY() + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(imageFill);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			noImage.setLocation(LEFT_MARGIN + (IMAGE_WIDTH - noImage.getWidth())/2, imageFill.getY() + (IMAGE_HEIGHT + noImage.getAscent())/2);
			add(noImage);
		}
		GLabel status;
		if(profile.getStatus() != null) {
			status = new GLabel(profile.getName() + profile.getStatus());
		} else {
			status = new GLabel("No current status");
		}
		status.setFont(PROFILE_STATUS_FONT);
		status.setLocation(LEFT_MARGIN, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		add(status);
		GLabel friendLabel = new GLabel("Friends:");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendLabel.setLocation(getWidth()/2, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
		add(friendLabel);
		Iterator<String> friendsIterator = profile.getFriends();
		int friendCounter = 0;
		while(friendsIterator.hasNext()) {
			friendCounter++;
			GLabel friend = new GLabel(friendsIterator.next());
			friend.setFont(PROFILE_FRIEND_FONT);
			friend.setLocation(getWidth()/2, friendLabel.getY() + friendCounter*(FRIEND_MARGIN + friend.getAscent()));
			add(friend);
		}
	}
	
}