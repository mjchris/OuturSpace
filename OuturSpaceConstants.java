/*
 * File: OuturSpaceConstants.java
 * --------------------------------
 * This file declares several constants that are shared by the
 * different modules in the OuturSpace application.  Any class
 * that implements this interface can use these constants.
 */

public interface OuturSpaceConstants {

	/** The width of the application window */
	public static final int APPLICATION_WIDTH = 800;

	/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 500;

	/** Name of font used to display the application message at the
	 *  bottom of the display canvas */
	public static final String MESSAGE_FONT = "Dialog-18";

	/** The number of pixels in the hortizontal margin between the 
	 *  left side of the canvas display area and the Name, Image, and 
	 *  Status components that are display in the profile */	
	public static final double LEFT_MARGIN = 20;	

	/** The number of pixels in the vertical margin between the top 
	 *  of the canvas display area and the top (NOT the baseline) of 
	 *  the Name component that is displayed in the profile */	
	public static final double TOP_MARGIN = 20;

}