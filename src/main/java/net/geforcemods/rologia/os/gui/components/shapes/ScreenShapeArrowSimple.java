package net.geforcemods.rologia.os.gui.components.shapes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.sun.javafx.scene.traversal.Direction;

import net.geforcemods.rologia.os.Rologia;
import net.geforcemods.rologia.os.gui.components.ScreenComponent;

public class ScreenShapeArrowSimple extends ScreenComponent {

	private int arrowHeight = 20;
	private int arrowPointDepth = 20;
	private int arrowThickness = 10;
	private Direction arrowDirection;
	
	private Color color;
	
	public ScreenShapeArrowSimple(Rologia os, int height, int depth, int thickness, Direction direction) {
		super(os);
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
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY());
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowThickness + arrowPointDepth, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY());
	
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY() + (arrowHeight * 2));
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY() + (arrowHeight * 2));
	        GL11.glVertex2i(getPosition().getX() + arrowThickness + arrowPointDepth, getPosition().getY() + arrowHeight);
        }
        else if(arrowDirection == Direction.LEFT) {
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth, getPosition().getY());
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + arrowThickness, getPosition().getY());
	
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth, getPosition().getY() + (arrowHeight * 2));
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + arrowThickness, getPosition().getY() + (arrowHeight * 2));
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY() + arrowHeight);
        }
        else if(arrowDirection == Direction.UP) {
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY());
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowThickness);
	
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY());
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowThickness);
	        GL11.glVertex2i(getPosition().getX() + (arrowHeight * 2) - arrowThickness, getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + (arrowHeight * 2), getPosition().getY() + arrowHeight);
        }
        else if(arrowDirection == Direction.DOWN) {
	        GL11.glVertex2i(getPosition().getX() + arrowThickness, getPosition().getY());
	        GL11.glVertex2i(getPosition().getX(), getPosition().getY());
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowHeight + arrowThickness);
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowHeight - arrowThickness);
	
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowHeight - arrowThickness);
	        GL11.glVertex2i(getPosition().getX() + arrowPointDepth + (arrowThickness * 2), getPosition().getY() + arrowHeight);
	        GL11.glVertex2i(getPosition().getX() + (arrowHeight * 2), getPosition().getY());
	        GL11.glVertex2i(getPosition().getX() + (arrowHeight * 2) - arrowThickness, getPosition().getY());
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
