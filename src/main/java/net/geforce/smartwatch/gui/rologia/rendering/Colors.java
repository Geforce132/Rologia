package net.geforce.smartwatch.gui.rologia.rendering;

import org.lwjgl.util.Color;

public class Colors {
	
	public static final Color BLACK = getNewColor(0, 0, 0, 1);
	public static final Color WHITE = getNewColor(255, 255, 255, 1);
	public static final Color RED = getNewColor(255, 0, 0, 1);
	public static final Color GREEN = getNewColor(0, 255, 0, 1);
	public static final Color BLUE = getNewColor(0, 0, 255, 1);
	public static final Color YELLOW = getNewColor(255, 255, 0, 1);
	public static final Color AQUA = getNewColor(0, 255, 255, 1);
	public static final Color MAGENTA = getNewColor(255, 0, 255, 1);
	
	public static Color getNewColor(int r, int g, int b) {
		return getNewColor(r, g, b, 1);
	}
	
	public static Color getNewColor(int r, int g, int b, int a) {
		return new Color(r, g, b, a);
	}

}
