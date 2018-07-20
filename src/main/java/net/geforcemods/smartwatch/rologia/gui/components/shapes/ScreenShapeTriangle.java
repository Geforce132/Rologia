package net.geforcemods.smartwatch.rologia.gui.components.shapes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;

public class ScreenShapeTriangle extends ScreenComponent {

	private int vertex1_x;
	private int vertex1_y;
	private int vertex2_x;
	private int vertex2_y;
	private int vertex3_x;
	private int vertex3_y;

	private Color color1;
	private Color color2;
	private Color color3;
	
	private boolean hasMultipleColors = false;
	
	public ScreenShapeTriangle(Screen screen, Color c1) {
		super(screen); // X-Y coordinates are for the centroid of the triangle.
		color1 = c1;
	}
	
	public ScreenShapeTriangle(Screen screen, Color c1, Color c2, Color c3) {
		super(screen);
		color1 = c1;
		color2 = c2;
		color3 = c3;
		
		hasMultipleColors = true;
	}

	public ScreenShapeTriangle(Screen screen, int v1x, int v1y, int v2x, int v2y, int v3x, int v3y, Color c1) {
		super(screen, (v1x + v2x + v3x) / 3, (v1y + v2y + v3y) / 3); // X-Y coordinates are for the centroid of the triangle.
		vertex1_x = v1x;
		vertex1_y = v1y;
		vertex2_x = v2x;
		vertex2_y = v2y;
		vertex3_x = v3x;
		vertex3_y = v3y;
		color1 = c1;
	}
	
	public ScreenShapeTriangle(Screen screen, int v1x, int v1y, int v2x, int v2y, int v3x, int v3y, Color c1, Color c2, Color c3) {
		super(screen, (v1x + v2x + v3x) / 3, (v1y + v2y + v3y) / 3); // X-Y coordinates are for the centroid of the triangle.
		vertex1_x = v1x;
		vertex1_y = v1y;
		vertex2_x = v2x;
		vertex2_y = v2y;
		vertex3_x = v3x;
		vertex3_y = v3y;
		color1 = c1;
		color2 = c2;
		color3 = c3;
		
		hasMultipleColors = true;
	}

	@Override
	public void drawComponent() {
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        if(hasMultipleColors)
        	GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        // Vertex 1
        GL11.glColor4f(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
        GL11.glVertex2f((float) vertex1_x, (float) vertex1_y);
        
        // Vertex 2
        if(hasMultipleColors)
        	GL11.glColor4f(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha());
        GL11.glVertex2f((float) vertex2_x, (float) vertex2_y);
        
        // Vertex 3
        if(hasMultipleColors)
        	GL11.glColor4f(color3.getRed(), color3.getGreen(), color3.getBlue(), color3.getAlpha());
        GL11.glVertex2f((float) vertex3_x, (float) vertex3_y);
        
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void mouseClick(int mouseX, int mouseY, int mouseButtonClicked) {
		
	}
	
	public void setVertices(int v1x, int v1y, int v2x, int v2y, int v3x, int v3y) {
		vertex1_x = v1x;
		vertex1_y = v1y;
		vertex2_x = v2x;
		vertex2_y = v2y;
		vertex3_x = v3x;
		vertex3_y = v3y;
	}
	
	public void setColor(Color rgbColor) {
		color1 = rgbColor;
		hasMultipleColors = false;
	}
	
	public void setColorGradient(Color c1, Color c2, Color c3) {
		color1 = c1;
		color2 = c2;
		color3 = c3;
	}
	
	//TODO Add formula for the width and height of the triangle here.
	@Override
	public int getWidth() {
		return 0;
	}
	
	@Override
	public int getHeight() {
		return 0;
	}

}
