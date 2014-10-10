/*
 * File: OuturSpaceDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * OuturSpace application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;
import java.io.*;

import acm.util.ErrorException;

public class OuturSpaceDatabase implements OuturSpaceConstants {
	
/* Instance variable for profileMap */
	public Map<String, OuturSpaceProfile> profileMap;

/* Constructor: OuturSpaceDatabase() */
/** 
 * This method takes care of any initialization needed for 
 * the database.
 */
	public OuturSpaceDatabase() {
		profileMap = new HashMap<String, OuturSpaceProfile>();
	}
	
/* Method addProfile(profile) */
/** 
 * This method adds the given profile to the database. If the 
 * name associated with the profile is the same as an existing 
 * name in the database, the existing profile is replaced by 
 * the new profile passed in.
 */
	public void addProfile(OuturSpaceProfile profile) {
		profileMap.put(profile.getUser(), profile);
	}

/* Method: getProfile(name) */
/** 
 * This method returns the profile associated with the given name 
 * in the database. If there is no profile in the database with 
 * the given name, the method returns null.
 */
	public OuturSpaceProfile getProfile(String name) {
		return profileMap.get(name);
	}

/* Method: containsProfile(name) */
/** 
 * This method returns true if there is a profile in the database 
 * that has the given name. It returns false otherwise.
 */
	public boolean containsProfile(String name) {
		return profileMap.containsKey(name);
	}

}