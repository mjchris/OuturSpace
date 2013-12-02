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
		// You fill this in
	}

}