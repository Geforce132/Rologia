package net.geforcemods.rologia.os.gui.utils;

import org.lwjgl.util.Color;

/**
 * Simple enum which lists common colors. Allows access to their 
 * {@link Color} instance and hex value.
 * 
 * @author Geforce
 */
public enum Colors {
	
	BLACK("black", 0, 0, 0, 1),
	GRAY("gray", 120, 120, 120, 1),
	DARK_GRAY("dark_gray", 60, 60, 60, 1),
	RED("red", 255, 0, 0, 1),
	DARK_RED("dark_red", 165, 0, 0, 1),
	GREEN("green", 0, 217, 3, 1),
	DARK_GREEN("dark_green", 8, 122, 35, 1),
	BLUE("blue", 0, 0, 255, 1),
	DARK_BLUE("dark_blue", 0, 0, 160, 255),
	YELLOW("yellow", 255, 255, 0, 1),
	GOLD("gold", 255, 200, 0, 1),
	AQUA("aqua", 0, 255, 255, 1),
	AQUAMARINE("aquamarine", 15, 150, 190, 1),
	PURPLE("purple", 170, 0, 170, 1),
	MAGENTA("magenta", 255, 0, 255, 1),
	WHITE("white", 255, 255, 255, 1);

	public final String name;
	public final Color color;
	public final int hexValue;
	
	private Colors(String colorName, int R, int G, int B, int A) {
		name = colorName;
		color = new Color(R, G, B, A);
		hexValue = GuiUtils.toHex(color);
	}

}
