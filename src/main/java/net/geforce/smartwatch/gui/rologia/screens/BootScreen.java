package net.geforce.smartwatch.gui.rologia.screens;

import net.geforce.smartwatch.gui.rologia.components.ScreenComponent;
import net.geforce.smartwatch.gui.rologia.components.ScreenText;
import net.geforce.smartwatch.gui.rologia.components.shapes.ScreenShapeRectangle;
import net.geforce.smartwatch.gui.rologia.rendering.Colors;

public class BootScreen extends Screen {

	private ScreenText testingComponent = new ScreenText("test", 100, 50, 4210752);
	private ScreenShapeRectangle rectangle = new ScreenShapeRectangle(100, 100, 100, 100, Colors.BLUE);

	@Override
	public void initializeScreen() {
		addComponent(testingComponent);
		addComponent(rectangle);
	}

	@Override
	public void editComponents() {

	}

	@Override
	public void onComponentClicked(ScreenComponent component, int mouseX, int mouseY) {
		if(component == rectangle)
			((ScreenShapeRectangle) component).setColor(Colors.GREEN);
	}

}
