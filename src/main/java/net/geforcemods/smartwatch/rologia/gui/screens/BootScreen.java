package net.geforcemods.smartwatch.rologia.gui.screens;

import com.sun.javafx.scene.traversal.Direction;

import net.geforcemods.smartwatch.rologia.gui.components.ScreenComponent;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenImage;
import net.geforcemods.smartwatch.rologia.gui.components.images.ScreenRotatingImage;
import net.geforcemods.smartwatch.rologia.gui.components.shapes.ScreenShapeArrowSimple;
import net.geforcemods.smartwatch.rologia.gui.components.shapes.ScreenShapeRectangle;
import net.geforcemods.smartwatch.rologia.gui.rendering.Colors;
import net.geforcemods.smartwatch.rologia.gui.rendering.Images;
import net.geforcemods.smartwatch.rologia.os.Rologia;

public class BootScreen extends Screen {

	private ScreenShapeRectangle rectangle = new ScreenShapeRectangle(this, 100, 100, Colors.BLUE);
	private ScreenRotatingImage loadingCircle = new ScreenRotatingImage(this, Images.LOADING_CIRCLE, 256, 256, 0.5F);
	private ScreenShapeArrowSimple arrow = new ScreenShapeArrowSimple(this, 14, 8, 3, Direction.RIGHT);
	private ScreenShapeArrowSimple arrow2 = new ScreenShapeArrowSimple(this, 12, 8, 3, Direction.LEFT);

	public BootScreen(Rologia OS, int screenX, int screenY) {
		super(OS, screenX, screenY);
	}

	@Override
	public void initializeScreen() {
		//getOS().setScreen(new HomeScreen(getOS(), getXPos(), getYPos()));
		addComponent(arrow);
		addComponent(arrow2);
		arrow.centerPosition(54, 0);
		arrow2.centerPosition(-54, 0);
		//addComponent(rectangle);
		//rectangle.centerPosition();
		loadingCircle.setPositionAndOrigin(this.getCenteredXForComponent(loadingCircle), this.getCenteredYForComponent(loadingCircle));
	}
	
	@Override
	public void updateScreen() {
		
	}

	@Override
	public void editComponent(ScreenComponent comp) {}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {
		if(component == rectangle)
			getOS().setScreen(new HomeScreen(getOS(), getXPos(), getYPos()));
	}

	@Override
	public ScreenImage getBackgroundImage() {
		return new ScreenImage(this, "minewatch:textures/gui/watch/default.png", WATCH_SCREEN_X_SIZE, WATCH_SCREEN_Y_SIZE);	
	}

}
