package net.geforce.smartwatch.gui.rologia.components;

public class ScreenText extends ScreenComponent {
	
	private String text;
	private int    colorValue;
	
	public ScreenText(String string, int x, int y, int color) {
		super(x, y);
		text = string;
		colorValue = color;
	}
	
	@Override
	public void drawComponent() {
		/*GL11.glPushMatrix();
		
		GL11.glTranslatef(xPos, yPos, 0);
		GL11.glRotatef(rotation, 0, 0, 1);
		GL11.glTranslatef(-xPos, -yPos, 0);
		
		GL11.glScalef(scale, scale, 1F);*/
		
		//this.drawString(getFontRenderer(), text, (int) (xPos / scale), (int) (yPos / scale), colorValue);
		this.drawString(getFontRenderer(), text, xPos, yPos, colorValue);
		//GL11.glPopMatrix();
	}
	
	@Override
	public int getWidth() {
		return getFontRenderer().getStringWidth(text);
	}
	
	@Override
	public int getHeight() {
		return (int) (20 * scale);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}

}
