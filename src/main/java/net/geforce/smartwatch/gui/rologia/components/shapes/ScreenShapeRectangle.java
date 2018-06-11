package net.geforce.smartwatch.gui.rologia.components.shapes;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;

public class ScreenShapeRectangle extends ScreenComponent {

	private int   width;
	private int   height;
	private int   RGBColor;
	
	public ScreenShapeRectangle(int x, int y, int w, int h, int color) {
		super(x, y);
		width = w;
		height = h;
		RGBColor = color;
	}

	@Override
	public void drawComponent() {
		drawRect(xPos, yPos, xPos + width, yPos + height, RGBColor);
	}
	
	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}
	
	public void setColor(int aRGBColor) {
		RGBColor = aRGBColor;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

}