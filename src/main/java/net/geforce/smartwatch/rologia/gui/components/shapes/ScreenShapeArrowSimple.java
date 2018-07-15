package net.geforce.smartwatch.rologia.gui.components.shapes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforce.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforce.smartwatch.rologia.gui.screens.Screen;

public class ScreenShapeArrowSimple extends ScreenComponent {

	private int arrowHeight = 20;
	private int arrowPointDepth = 20;
	private int arrowThickness = 10;
	
	private Color color;
	
	public ScreenShapeArrowSimple(Screen screen, int height, int depth, int thickness) {
		super(screen);
		arrowHeight = height;
		arrowPointDepth = depth;
		arrowThickness = thickness;
	}

	@Override
	public void drawComponent() {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor4f(255F, 0F, 0F, 1F);

        GL11.glVertex2i(getXPos(), getYPos());
        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos() + arrowHeight);
        GL11.glVertex2i(getXPos() + arrowThickness + arrowPointDepth, getYPos() + arrowHeight);
        GL11.glVertex2i(getXPos() + arrowThickness, getYPos());
        
        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos() + arrowHeight);
        GL11.glVertex2i(getXPos(), getYPos() + (arrowHeight * 2));
        GL11.glVertex2i(getXPos() + arrowThickness, getYPos() + (arrowHeight * 2));
        GL11.glVertex2i(getXPos() + arrowThickness + arrowPointDepth, getYPos() + arrowHeight);
        
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {

	}

	@Override
	public int getWidth() {
		return arrowThickness + arrowPointDepth;
	}

	@Override
	public int getHeight() {
		return arrowHeight * 2;
	}

}
