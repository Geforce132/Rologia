package net.geforce.smartwatch.gui.rologia.screens;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.images.ScreenImage;
import net.geforce.smartwatch.gui.rologia.components.images.ScreenRotatingImage;
import net.geforce.smartwatch.gui.rologia.components.shapes.ScreenShapeRectangle;
import net.geforce.smartwatch.gui.rologia.rendering.Colors;
import net.geforce.smartwatch.gui.rologia.rendering.Images;

public class BootScreen extends Screen {

	private ScreenShapeRectangle rectangle = new ScreenShapeRectangle(100, 100, 100, 100, Colors.BLUE);
	private ScreenRotatingImage loadingCircle = new ScreenRotatingImage(Images.LOADING_CIRCLE, getXPos(), getYPos(), 256, 256, 0.5F);

	public BootScreen(int screenX, int screenY) {
		super(screenX, screenY);
	}

	@Override
	public void initializeScreen() {
		loadingCircle.setPositionAndOrigin(this.getCenteredXForComponent(loadingCircle), this.getCenteredYForComponent(loadingCircle));
	}

	@Override
	public void editComponent(ScreenComponent comp) {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage("minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
