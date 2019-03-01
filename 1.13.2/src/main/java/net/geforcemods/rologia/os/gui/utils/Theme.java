package net.geforcemods.rologia.os.gui.utils;

import net.geforcemods.rologia.os.gui.utils.Colors.Color;

/**
 * This class contains the colors used by every component in GUIs. It starts off
 * empty, but is populated by ResourceLoader.loadThemes() once a GUI is opened.
 */
public class Theme {
	
	public String THEME_NAME;

	public Color BACKGROUND_OVERLAY;
	
	public Color DEBUG_TEXT;
	public Color DEBUG_TEXT_HOVERING;
	
	public Color BUTTON_OUTLINE;
	public Color BUTTON_INTERIOR;
	public Color BUTTON_INTERIOR_HOVERING;
	public Color BUTTON_TEXT;
	
	public Color SETTINGS_TEXT;

	public Color SELECTION_TEXT;

	public Color TEXT_FIELD_LINE;
	public Color TEXT_FIELD_TEXT;

	public Color APP_BAR_HOVERING_TEXT;

	public Color STATUS_BAR;
	public Color STATUS_BAR_CLOCK;
	
	public Color INPUT_TEXT_PROMPT;
	public Color INPUT_YES_NO_PROMPT;
	
	public Color NOTIFICATION_TITLE;
	public Color NOTIFICATION_BODY;
	
	public Color IM_BACKGROUND;


	public String getName() {
		return THEME_NAME;
	}

}
