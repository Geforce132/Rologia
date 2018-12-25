package net.geforcemods.rologia.os.gui.utils;

import org.lwjgl.util.Color;

/**
 * Simple enum which lists common colors. Allows access to their 
 * {@link Color} instance and hex value.
 * 
 * @author Geforce
 */
public enum Colors {
	
	BLACK(0, 0, 0, 1),
	GRAY(120, 120, 120, 1),
	DARK_GRAY(60, 60, 60, 1),
	WHITE(255, 255, 255, 1),
	RED(255, 0, 0, 1),
	DARK_RED(165, 0, 0, 1),
	GREEN(0, 217, 3, 1),
	DARK_GREEN(8, 122, 35, 1),
	BLUE(0, 0, 255, 1),
	DARK_BLUE(0, 0, 160, 255),
	YELLOW(255, 255, 0, 1),
	GOLD(255, 200, 0, 1),
	AQUA(0, 255, 255, 1),
	AQUAMARINE(15, 150, 190, 1),
	PURPLE(170, 0, 170, 1),
	MAGENTA(255, 0, 255, 1);
	
	public final Color color;
	public final int hexValue;
	
	private Colors(int R, int G, int B, int A) {
		color = new Color(R, G, B, A);
		String hex = String.format("%02X%02X%02X", R, G, B);  
		hexValue = Integer.parseInt(hex, 16);
	}

}
