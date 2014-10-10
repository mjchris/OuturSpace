/*
 * File: OuturSpacePost.java
 * ------------------------------
 * This class keeps track of all the information for one post
 * in the OuturSpace social network.  Each post contains a
 * location, start date/time, and end date/time.
 */

public class OuturSpacePost implements OuturSpaceConstants {
	
/* Instance variables for name, image, status, and friends */
	public String user;
	public String location;
	public String dateStart;
	public String timeStart;
	public String dateEnd;
	public String timeEnd;
	
/* Constructor: OuturSpacePost() */
/** 
 * This method takes care of any initialization needed for
 * the post.
 */
	public OuturSpacePost() {
		this.user = null;
		this.location = null;
		this.dateStart = null;
		this.timeStart = null;
		this.dateEnd = null;
		this.timeEnd = null;
	}
	
/* Method: setUser() */
/** This method sets the user associated with the post. */
	public void setUser(String user) {
		this.user = user;
	}

/* Method: getUser() */
/** This method gets the user associated with the post. */
	public String getUser() {
		return user;
	}
	
/* Method: setLocation() */
/** This method sets the location associated with the post. */
	public void setLocation(String location) {
		this.location = location;
	}
	
/* Method: getLocation() */
/** This method returns the location associated with the post. */
	public String getLocation() {
		return location;
	}
	
/* Method: setDateStart() */
/** This method sets the start date associated with the post. */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	
/* Method: setTimeStart() */
/** This method sets the start time associated with the post. */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	
/* Methods: getStart() */
/** These methods return the start date/time associated with the post. */
	public int getMonthi() {
		int monthi = Integer.parseInt(dateStart.substring(0, 2));
		return monthi;
	}
	public int getDatei() {
		int datei = Integer.parseInt(dateStart.substring(3, 5));
		return datei;
	}
	public int getYeari() {
		int yeari = Integer.parseInt(dateStart.substring(6));
		return yeari;
	}
	public int getHri() {
		int hri = Integer.parseInt(timeStart.substring(0, 2));
		return hri;
	}
	public int getMini() {
		int mini = Integer.parseInt(timeStart.substring(3));
		return mini;
	}
	public String getStart() {
		String start = dateStart + " @ " + timeStart;
		return start;
	}
	
/* Method: setDateEnd() */
/** This method sets the end date associated with the post. */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

/* Method: setTimeEnd() */
/** This method sets the end time associated with the post. */
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
/* Methods: getEnd() */
/** These methods return the end date/time associated with the post. */
	public int getMonthf() {
		int monthf = Integer.parseInt(dateEnd.substring(0, 2));
		return monthf;
	}
	public int getDatef() {
		int datef = Integer.parseInt(dateEnd.substring(3, 5));
		return datef;
	}
	public int getYearf() {
		int yearf = Integer.parseInt(dateEnd.substring(6));
		return yearf;
	}
	public int getHrf() {
		int hrf = Integer.parseInt(timeEnd.substring(0, 2));
		return hrf;
	}
	public int getMinf() {
		int minf = Integer.parseInt(timeEnd.substring(3));
		return minf;
	}
	public String getEnd() {
		String end = dateEnd + " @ " + timeEnd;
		return end;
	}
	
}