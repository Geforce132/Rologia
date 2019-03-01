package net.geforcemods.rologia.os.gui.utils;

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

	public static class Color {

		private byte red, green, blue, alpha;

		public Color() {
			this(0, 0, 0, 255);
		}

		public Color(int r, int g, int b) {
			this(r, g, b, 255);
		}

		public Color(int r, int g, int b, int a) {
			set(r, g, b, a);
		}

		public void set(int r, int g, int b, int a) {
			red = (byte) r;
			green = (byte) g;
			blue = (byte) b;
			alpha = (byte) a;
		}

		public void set(int r, int g, int b) {
			set(r, g, b, 255);
		}

		public int getRed() {
			return red & 0xFF;
		}

		public int getGreen() {
			return green & 0xFF;
		}

		public int getBlue() {
			return blue & 0xFF;
		}

		public int getAlpha() {
			return alpha & 0xFF;
		}

		public void setRed(int red) {
			this.red = (byte) red;
		}

		public void setGreen(int green) {
			this.green = (byte) green;
		}

		public void setBlue(int blue) {
			this.blue = (byte) blue;
		}

		public void setAlpha(int alpha) {
			this.alpha = (byte) alpha;
		}
	}
}