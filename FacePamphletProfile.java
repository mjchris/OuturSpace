/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
/* Instance variables for name, image, status, and friends */
	public String name;
	public GImage image;
	public String status;
	public ArrayList<String> friends;
	
/* Instance variables for imageMap, statusMap, and friendsMap */
	public Map<String, GImage> imageMap = new HashMap<String, GImage>();
	public Map<String, String> statusMap = new HashMap<String, String>();
	public Map<String, ArrayList<String>> friendsMap = new HashMap<String, ArrayList<String>>();
	
/* Constructor: FacePamphletProfile(name) */
/** 
 * This method takes care of any initialization needed for
 * the profile.
 */
	public FacePamphletProfile(String name) {
		this.name = name;
		image = imageMap.get(name);
		status = statusMap.get(name);
		friends = friendsMap.get(name);
	}

/* Method: getName() */
/** This method returns the name associated with the profile. */
	public String getName() {
		return name;
	}

/* Method: getImage() */
/** 
 * This method returns the image associated with the profile.  
 * If there is no image associated with the profile, the method
 * returns null.
 */
	public GImage getImage() {
		return image;
	}

/* Method: setImage(image) */
/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		imageMap.remove(name);
		image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		imageMap.put(name, image);
	}

/* Method: getStatus() */
/** 
 * This method returns the status associated with the profile.
 * If there is no status associated with the profile, the method
 * returns the empty string ("").
 */ 
	public String getStatus() {
		if(status == null) {
			return "";
		}
		return status;
	}

/* Method: setStatus(status) */
/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		statusMap.remove(status);
		statusMap.put(name, status);
	}

/* Method: addFriend(friend) */
/** 
 * This method adds the named friend to this profile's list of 
 * friends.  It returns true if the friend's name was not already
 * in the list of friends for this profile (and the name is added 
 * to the list).  The method returns false if the given friend name
 * was already in the list of friends for this profile (in which 
 * case, the given friend name is not added to the list of friends 
 * a second time.)
 */
	public boolean addFriend(String friend) {
		boolean result = !friends.contains(friend);
		if(result) {
			friends.add(friend);
		}
		friendsMap.remove(friends);
		friendsMap.put(name, friends);
		return result;
	}

/* Method: removeFriend(friend) */
/** 
 * This method removes the named friend from this profile's list
 * of friends.  It returns true if the friend's name was in the 
 * list of friends for this profile (and the name was removed from
 * the list).  The method returns false if the given friend name 
 * was not in the list of friends for this profile (in which case,
 * the given friend name could not be removed.)
 */
	public boolean removeFriend(String friend) {
		boolean result = friends.contains(friend);
		if(result) {
			friends.remove(friend);
		}
		friendsMap.remove(friends);
		friendsMap.put(name, friends);
		return result;
	}

/* Method: getFriends() */
/** 
 * This method returns an iterator over the list of friends 
 * associated with the profile.
 */ 
	public Iterator<String> getFriends() {
		Iterator<String> friendsIterator = friends.iterator();
		return friendsIterator;
	}
	
/* Method toString() */
/** 
 * This method returns a string representation of the profile.  
 * This string is of the form: "name (status): list of friends", 
 * where name and status are set accordingly and the list of 
 * friends is a comma separated list of the names of all of the 
 * friends in this profile.
 */ 
	public String toString() {
		String toString = name + " (";
		if(statusMap.get(name) != null) {
			toString += statusMap.get(name);
		}
		toString += "):";
		if(friendsMap.get(name) != null) {
			while(getFriends().hasNext()) {
				toString += " " + getFriends().next();
			}
		}
		return toString;
	}
	
}