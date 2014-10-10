/*
 * File: OuturSpaceProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the OuturSpace social network.  Each profile contains a
 * user, a password, a phone, and lists of posts.
 */

import acm.graphics.*;
import java.util.*;

public class OuturSpaceProfile implements OuturSpaceConstants {
	
/* Instance variables for name, image, status, and friends */
	public String user;
	public String pw;
	public String phone;
	public ArrayList<OuturSpacePost> orbits;
	public ArrayList<OuturSpacePost> crashlands;
	public ArrayList<OuturSpacePost> cosmos;
	
/* Constructor: OuturSpaceProfile(name) */
/** 
 * This method takes care of any initialization needed for
 * the profile.
 */
	public OuturSpaceProfile(String user) {
		this.user = user;
		pw = null;
		phone = "";
		orbits = new ArrayList<OuturSpacePost>();
		crashlands = new ArrayList<OuturSpacePost>();
		cosmos = new ArrayList<OuturSpacePost>();
	}

/* Method: getName() */
/** This method returns the name associated with the profile. */
	public String getUser() {
		return user;
	}

/* Method: getImage() */
/** 
 * This method returns the image associated with the profile.  
 * If there is no image associated with the profile, the method
 * returns null.
 */
	public String getPw() {
		return pw;
	}

/* Method: setImage(image) */
/** This method sets the image associated with the profile. */ 
	public void setPw(String pw) {
		this.pw = pw;
	}

/* Method: getStatus() */
/** 
 * This method returns the status associated with the profile.
 * If there is no status associated with the profile, the method
 * returns the empty string ("").
 */ 
	public String getPhone() {
		return phone;
	}

/* Method: setStatus(status) */
/** This method sets the status associated with the profile. */ 
	public void setPhone(String phone) {
		this.phone = phone;
	}

/* Method: addOrbit(post) */
/**
 * This method adds a post to list of orbits.
 */
	public void addOrbit(OuturSpacePost post) {
		orbits.add(post);
	}
	
/* Method: addCrashland(post) */
/**
 * This method adds a post to list of crashlands.
 */
	public void addCrashland(OuturSpacePost post) {
		crashlands.add(post);
	}
	
/* Method: addCosmo(post) */
/**
 * This method adds a post to list of cosmos.
 */
	public void addCosmo(OuturSpacePost post) {
		cosmos.add(post);
	}

}