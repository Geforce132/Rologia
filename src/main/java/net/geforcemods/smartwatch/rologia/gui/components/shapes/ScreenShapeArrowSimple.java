package net.geforcemods.smartwatch.rologia.gui.components.shapes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.sun.javafx.scene.traversal.Direction;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;

public class ScreenShapeArrowSimple extends ScreenComponent {

	private int arrowHeight = 20;
	private int arrowPointDepth = 20;
	private int arrowThickness = 10;
	private Direction arrowDirection;
	
	private Color color;
	
	public ScreenShapeArrowSimple(Screen screen, int height, int depth, int thickness, Direction direction) {
		super(screen);
		arrowHeight = height;
		arrowPointDepth = depth;
		arrowThickness = thickness;
		arrowDirection = direction;
	}

	@Override
	public void drawComponent() {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glColor4f(255F, 0F, 0F, 1F);

        if(arrowDirection == Direction.RIGHT) {
	        GL11.glVertex2i(getXPos(), getYPos());
	        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowThickness + arrowPointDepth, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos());
	
	        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos(), getYPos() + (arrowHeight * 2));
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos() + (arrowHeight * 2));
	        GL11.glVertex2i(getXPos() + arrowThickness + arrowPointDepth, getYPos() + arrowHeight);
        }
        else if(arrowDirection == Direction.LEFT) {
	        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos());
	        GL11.glVertex2i(getXPos(), getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowPointDepth + arrowThickness, getYPos());
	
	        GL11.glVertex2i(getXPos(), getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowPointDepth, getYPos() + (arrowHeight * 2));
	        GL11.glVertex2i(getXPos() + arrowPointDepth + arrowThickness, getYPos() + (arrowHeight * 2));
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos() + arrowHeight);
        }
        else if(arrowDirection == Direction.UP) {
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos());
	        GL11.glVertex2i(getXPos(), getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowThickness);
	
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos());
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowThickness);
	        GL11.glVertex2i(getXPos() + (arrowHeight * 2) - arrowThickness, getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + (arrowHeight * 2), getYPos() + arrowHeight);
        }
        else if(arrowDirection == Direction.DOWN) {
	        GL11.glVertex2i(getXPos() + arrowThickness, getYPos());
	        GL11.glVertex2i(getXPos(), getYPos());
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowHeight + arrowThickness);
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowHeight - arrowThickness);
	
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowHeight - arrowThickness);
	        GL11.glVertex2i(getXPos() + arrowPointDepth + (arrowThickness * 2), getYPos() + arrowHeight);
	        GL11.glVertex2i(getXPos() + (arrowHeight * 2), getYPos());
	        GL11.glVertex2i(getXPos() + (arrowHeight * 2) - arrowThickness, getYPos());
        }

        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {

	}

	@Override
	public int getWidth() {
		if(arrowDirection == Direction.RIGHT || arrowDirection == Direction.LEFT)
			return arrowThickness + arrowPointDepth;
		else
			return arrowHeight * 2;
	}

	@Override
	public int getHeight() {
		if(arrowDirection == Direction.RIGHT || arrowDirection == Direction.LEFT)
			return arrowHeight * 2;
		else
			return (arrowThickness * 2) + arrowPointDepth;
	}

}
