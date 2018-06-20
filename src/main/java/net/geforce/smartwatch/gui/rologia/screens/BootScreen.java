package net.geforce.smartwatch.gui.rologia.screens;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.ScreenImage;
import net.geforce.smartwatch.gui.rologia.components.ScreenRotatingImage;
import net.geforce.smartwatch.gui.rologia.components.ScreenText;
import net.geforce.smartwatch.gui.rologia.components.shapes.ScreenShapeRectangle;
import net.geforce.smartwatch.gui.rologia.rendering.Colors;
import net.geforce.smartwatch.gui.rologia.rendering.Images;

public class BootScreen extends Screen {

	private ScreenText testingComponent = new ScreenText("test", 100, 50, 4210752);
	private ScreenShapeRectangle rectangle = new ScreenShapeRectangle(100, 100, 100, 100, Colors.BLUE);
	private ScreenRotatingImage loadingCircle = new ScreenRotatingImage(Images.LOADING_CIRCLE, getXPos(), getYPos(), 256, 256, 0.5F);

	public BootScreen(int screenX, int screenY) {
		super(screenX, screenY);
	}

	@Override
	public void initializeScreen() {
		loadingCircle.setPositionAndOrigin(this.getCenteredXForComponent(loadingCircle), this.getCenteredYForComponent(loadingCircle));
		addComponent(loadingCircle);
	}

	@Override
	public void editComponents() {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {
		if(component == rectangle)
			((ScreenShapeRectangle) component).setColor(Colors.GREEN);
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage("minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
