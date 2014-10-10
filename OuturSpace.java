/* 
 * File: OuturSpace.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

public class OuturSpace extends GraphicsProgram implements ComponentListener, MouseListener, OuturSpaceConstants {
	
/* Instance variable for OuturSpaceProfile currentProfile and currentPost */
	private OuturSpaceProfile currentProfile = null;
	
/* Instance variable for OuturSpaceDatabase database */
	private OuturSpaceDatabase database = new OuturSpaceDatabase();
	
/* Instance variable for page display */
	private GImage pageDisplay;

/* Instance variable for stage in page map */
	private String stage = "Hero";

/* Method: main(args) */
	public static void main(String[] args) {
		new OuturSpace().start(args);
	}

/* Constructor: OuturSpace() */
	public OuturSpace() {
	}

/* Method: init() */
/**
 * This method initializes the application.
 */
	public void init() {
		addComponentListener(this);
		addMouseListeners();
    }

/* Method: componentResized(e) */
/**
 * This method refreshes the display when resized.
 */
	public void componentResized(ComponentEvent e) {
		displayPage();
	}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

/* Method: mouseClicked(e) */
/**
 * This method controls actions if mouse is clicked.
 */
	public void mouseClicked(MouseEvent e) {
		switch(stage) {
		case "Hero":
			// Click to enter
			if(e.getX() > 0.31*getWidth() && e.getX() < 0.69*getWidth() && e.getY() > 0.8*getHeight() && e.getY() < 0.87*getHeight()) {
				String user = JOptionPane.showInputDialog(null, null, "Username/email");
				if(database.containsProfile(user)) {
					currentProfile = database.getProfile(user);
					String pw = JOptionPane.showInputDialog(null, null, "Password");
					if(pw.equals(currentProfile.getPw())) {
						stage = "Home";
						displayPage();
					} else {
						while(!pw.equals(currentProfile.getPw())) {
							pw = JOptionPane.showInputDialog(null, null, "Incorrect Password");
						}
						if(pw.equals(currentProfile.getPw())) {
							stage = "Home";
							displayPage();
						}
					}
					stage = "Home";
					displayPage();
				} else {
					int signUp = JOptionPane.showConfirmDialog(null, "That username does not exist. Would you like to create an account?", "Sign Up", JOptionPane.YES_NO_OPTION);
					if(signUp == JOptionPane.YES_OPTION) {
						user = JOptionPane.showInputDialog(null, null, user);
						while(!user.endsWith("@stanford.edu")) {
							user = JOptionPane.showInputDialog(null, null, "Please enter a valid Stanford email");
						}
						currentProfile = new OuturSpaceProfile(user);
						database.addProfile(currentProfile);
						String pw = JOptionPane.showInputDialog(null, null, "Password");
						String pwConf = JOptionPane.showInputDialog(null, null, "Confirm password");
						if(!pw.equals(pwConf)) {
							while(!pw.equals(pwConf)) {
								pwConf = JOptionPane.showInputDialog(null, null, "Password did not match");
							}
							currentProfile.setPw(pw);
							String phone = JOptionPane.showInputDialog(null, null, "Phone (##########)");
							if(phone.length() != 10) {
								while(phone.length() != 10) {
									phone = JOptionPane.showInputDialog(null, null, "Enter a valid phone (##########)");
								}
							}
						} else {
							currentProfile.setPw(pw);
							String phone = JOptionPane.showInputDialog(null, null, "Phone (##########)");
							if(phone.length() != 10) {
								while(phone.length() != 10) {
									phone = JOptionPane.showInputDialog(null, null, "Enter a valid phone (##########)");
								}
							}
							currentProfile.setPhone(phone);
						}
						stage = "Home";
						displayPage();
					} else {
						stage = "Hero";
						displayPage();
					}
				}
			}
			break;
		case "Home":
			// Click to post
			if(e.getX() > 0.34*getWidth() && e.getX() < 0.49*getWidth() && e.getY() > 0.75*getHeight() && e.getY() < 0.82*getHeight()) {
				Color background = new Color(34,34,34);
				setBackground(background);
				stage = "Post";
				displayPage();
			}
			// Click to search
			if(e.getX() > 0.51*getWidth() && e.getX() < 0.66*getWidth() && e.getY() > 0.75*getHeight() && e.getY() < 0.82*getHeight()) {
				Color background = new Color(142,141,140);
				setBackground(background);
				stage = "Search";
				displayPage();
			}
			// Click to sign out
			if(e.getX() > 0.92*getWidth() && e.getX() < 0.99*getWidth() && e.getY() > 0.95*getHeight() && e.getY() < 0.99*getHeight()) {
				currentProfile = null;
				stage = "Hero";
				displayPage();
			}
			break;
		case "Post":
			// Click to orbit
			if(e.getX() > 0.26*getWidth() && e.getX() < 0.4*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				OuturSpacePost post = createPost();
				currentProfile.addOrbit(post);
				stage = "postConfirm";
				displayPage();
			}
			// Click to crashland
			if(e.getX() > 0.43*getWidth() && e.getX() < 0.57*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				OuturSpacePost post = createPost();
				currentProfile.addCrashland(post);
				stage = "postConfirm";
				displayPage();
			}
			// Click to cosmo
			if(e.getX() > 0.6*getWidth() && e.getX() < 0.74*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				OuturSpacePost post = createPost();
				currentProfile.addCosmo(post);
				stage = "postConfirm";
				displayPage();
			}
			// Click to sign out
			if(e.getX() > 0.92*getWidth() && e.getX() < 0.99*getWidth() && e.getY() > 0.95*getHeight() && e.getY() < 0.99*getHeight()) {
				currentProfile = null;
				stage = "Hero";
				displayPage();
			}
			// Click to go home
			if(e.getX() > 0.49*getWidth() && e.getX() < 0.51*getWidth() && e.getY() > 0.02*getHeight() && e.getY() < 0.04*getHeight()) {
				stage = "Home";
				displayPage();
			}
			break;
		case "postConfirm":
			// Click to sign out
			if(e.getX() > 0.92*getWidth() && e.getX() < 0.99*getWidth() && e.getY() > 0.95*getHeight() && e.getY() < 0.99*getHeight()) {
				setBackground(Color.WHITE);
				currentProfile = null;
				stage = "Hero";
				displayPage();
			}
			// Click to go home
			if(e.getX() > 0.49*getWidth() && e.getX() < 0.51*getWidth() && e.getY() > 0.02*getHeight() && e.getY() < 0.04*getHeight()) {
				setBackground(Color.WHITE);
				stage = "Home";
				displayPage();
			}
			break;
		case "Search":
			// Click to orbit
			if(e.getX() > 0.26*getWidth() && e.getX() < 0.4*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				ArrayList<OuturSpacePost> postList = findPosts("orbits");
				listPosts(postList);
			}
			// Click to crashland
			if(e.getX() > 0.43*getWidth() && e.getX() < 0.57*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				ArrayList<OuturSpacePost> postList = findPosts("crashlands");
				listPosts(postList);
			}
			// Click to cosmo
			if(e.getX() > 0.6*getWidth() && e.getX() < 0.74*getWidth() && e.getY() > 0.56*getHeight() && e.getY() < 0.68*getHeight()) {
				ArrayList<OuturSpacePost> postList = findPosts("cosmos");
				listPosts(postList);
			}
			// Click to sign out
			if(e.getX() > 0.92*getWidth() && e.getX() < 0.99*getWidth() && e.getY() > 0.95*getHeight() && e.getY() < 0.99*getHeight()) {
				currentProfile = null;
				stage = "Hero";
				displayPage();
			}
			// Click to go home
			if(e.getX() > 0.49*getWidth() && e.getX() < 0.51*getWidth() && e.getY() > 0.02*getHeight() && e.getY() < 0.04*getHeight()) {
				stage = "Home";
				displayPage();
			}
			break;
		case "H-Results":
		case "S-Results":
		case "P-Results":
			// Click to sign out
			if(e.getX() > 0.92*getWidth() && e.getX() < 0.99*getWidth() && e.getY() > 0.95*getHeight() && e.getY() < 0.99*getHeight()) {
				currentProfile = null;
				stage = "Hero";
				displayPage();
			}
			// Click to go home
			if(e.getX() > 0.49*getWidth() && e.getX() < 0.51*getWidth() && e.getY() > 0.02*getHeight() && e.getY() < 0.04*getHeight()) {
				stage = "Home";
				displayPage();
			}
			break;
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(stage == "Home") {
			// Hover on post
			if(e.getX() > 0.34*getWidth() && e.getX() < 0.49*getWidth() && e.getY() > 0.75*getHeight() && e.getY() < 0.82*getHeight()) {
				Color background = new Color(34,34,34);
				setBackground(background);
			} else {
				// Hover on search
				if(e.getX() > 0.51*getWidth() && e.getX() < 0.66*getWidth() && e.getY() > 0.75*getHeight() && e.getY() < 0.82*getHeight()) {
					Color background = new Color(142,141,140);
					setBackground(background);
				} else {
					setBackground(Color.WHITE);
				}
			}
		}
	}
	
/* Method: displayPage() */
/**
 * This method displays the page.
 */
	public void displayPage() {
		removeAll();
		switch(stage) {
		case "Hero":
			pageDisplay = new GImage("Hero.png");
			break;
		case "Home":
			pageDisplay = new GImage("Home.png");
			break;
		case "Post":
			pageDisplay = new GImage("P-Reservation-Selection.png");
			break;
		case "postConfirm":
			pageDisplay = new GImage("P-Confirm.png");
			break;
		case "Search":
			pageDisplay = new GImage("S-Reservation-Selection.png");
			break;
		case "H-Results":
			pageDisplay = new GImage("H-Results.png");
			break;
		case "S-Results":
			pageDisplay = new GImage("S-Results.png");
			break;
		case "P-Results":
			pageDisplay = new GImage("P-Results.png");
			break;
		}
		pageDisplay.setSize(getWidth(), getHeight());
		add(pageDisplay, 0, 0);
	}

/* Method: createPost() */
/**
 * This method prompts user to create a post.
 */
	public OuturSpacePost createPost() {
		OuturSpacePost newPost = new OuturSpacePost();
		newPost.setUser(currentProfile.getUser());
		String location = JOptionPane.showInputDialog(null, null, "Location");
		newPost.setLocation(location);
		String dateStart = JOptionPane.showInputDialog(null, null, "Start date (MM-DD-YY)");
		newPost.setDateStart(dateStart);
		String timeStart = JOptionPane.showInputDialog(null, null, "Start time (HH:MM)");
		newPost.setTimeStart(timeStart);
		String dateEnd = JOptionPane.showInputDialog(null, null, "End date (MM-DD-YY)");
		newPost.setDateEnd(dateEnd);
		String timeEnd = JOptionPane.showInputDialog(null, null, "End time (HH:MM)");
		newPost.setTimeEnd(timeEnd);
		return newPost;
	}

/* Method: findPosts(type) */
/**
 * This method prompts user for search criteria.
 */
	public ArrayList<OuturSpacePost> findPosts(String type) {
		ArrayList<OuturSpacePost> postList = new ArrayList<OuturSpacePost>();
		String dateStart = JOptionPane.showInputDialog(null, null, "Start date (MM-DD-YY)");
			int monthi = Integer.parseInt(dateStart.substring(0, 2));
			int datei = Integer.parseInt(dateStart.substring(3, 5));
			int yeari = Integer.parseInt(dateStart.substring(6));
		String timeStart = JOptionPane.showInputDialog(null, null, "Start time (HH:MM)");
			int hri = Integer.parseInt(timeStart.substring(0, 2));
			int mini = Integer.parseInt(timeStart.substring(3));
		String dateEnd = JOptionPane.showInputDialog(null, null, "End date (MM-DD-YY)");
			int monthf = Integer.parseInt(dateEnd.substring(0, 2));
			int datef = Integer.parseInt(dateEnd.substring(3, 5));
			int yearf = Integer.parseInt(dateEnd.substring(6));
		String timeEnd = JOptionPane.showInputDialog(null, null, "End time (HH:MM)");
			int hrf = Integer.parseInt(timeEnd.substring(0,2));
			int minf = Integer.parseInt(timeEnd.substring(3));
		Iterator<String> profileIter = database.profileMap.keySet().iterator();
		while(profileIter.hasNext()) {
			OuturSpaceProfile profileTemp = database.getProfile(profileIter.next());
			Iterator<OuturSpacePost> postIter = null;
			if(type.equals("orbits")) {
				postIter = profileTemp.orbits.iterator();
				stage = "H-Results";
			} else if(type.equals("crashlands")) {
				postIter = profileTemp.crashlands.iterator();
				stage = "S-Results";
			} else if(type.equals("cosmos")) {
				postIter = profileTemp.cosmos.iterator();
				stage = "P-Results";
			}
			while(postIter.hasNext()) {
				OuturSpacePost postTemp = postIter.next();
				if(postTemp.getYeari() < yeari && postTemp.getYearf() > yearf) {
					postList.add(postTemp);
				} else {
					if(postTemp.getYeari() == yeari && postTemp.getYearf() == yearf) {
						if(postTemp.getMonthi() < monthi && postTemp.getMonthf() > monthf) {
							postList.add(postTemp);
						} else {
							if(postTemp.getMonthi() == monthi && postTemp.getMonthf() == monthf) {
								if(postTemp.getDatei() < datei && postTemp.getDatef() > datef) {
									postList.add(postTemp);
								} else {
									if(postTemp.getDatei() == datei && postTemp.getDatef() == datef) {
										if(postTemp.getHri() < hri && postTemp.getHrf() > hrf) {
											postList.add(postTemp);
										} else {
											if(postTemp.getHri() == hri && postTemp.getHrf() == hrf) {
												if(postTemp.getMini() < mini && postTemp.getMinf() > minf) {
													postList.add(postTemp);
												} else {
													if(postTemp.getMini() == mini && postTemp.getMinf() == minf) {
														postList.add(postTemp);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return postList;
	}
	
/* Method: listPosts(postList) */
/**
 * This method lists posts that match search criteria.
 */
	public void listPosts(ArrayList<OuturSpacePost> listPost) {
		displayPage();
		if(listPost.isEmpty()) {
			GLabel label = new GLabel("No results found.");
			label.setFont(MESSAGE_FONT);
			label.setLocation(0.64*getWidth() - label.getWidth()/2, 0.58*getHeight() + label.getHeight()/2);
			add(label);
		} else {
			double x = 0.36*getWidth() + LEFT_MARGIN;
			double y = 0.17*getHeight() + TOP_MARGIN;
			Iterator<OuturSpacePost> postIter = listPost.iterator();
			int i = 0;
			while(postIter.hasNext()) {
				i++;
				y = y + (i - 1)*0.16*getHeight();
				OuturSpacePost post = postIter.next();
				GLabel location = new GLabel("Location: " + post.getLocation());
				location.setFont(MESSAGE_FONT);
				location.setLocation(x, y);
				add(location);
				GLabel start = new GLabel("Start: " + post.getStart());
				start.setFont(MESSAGE_FONT);
				start.setLocation(x, location.getY() + start.getHeight());
				add(start);
				GLabel end = new GLabel("End: " + post.getEnd());
				end.setFont(MESSAGE_FONT);
				end.setLocation(x, start.getY() + end.getHeight());
				add(end);
				GLabel user = new GLabel("Email: " + post.getUser());
				user.setFont(MESSAGE_FONT);
				user.setLocation(x + 0.2*getWidth(), y);
				add(user);
				GLabel phone = new GLabel("Phone: " + database.getProfile(post.getUser()).getPhone());
				phone.setFont(MESSAGE_FONT);
				phone.setLocation(x + 0.2*getWidth(), y + phone.getHeight());
				add(phone);
			}
		}
	}

}