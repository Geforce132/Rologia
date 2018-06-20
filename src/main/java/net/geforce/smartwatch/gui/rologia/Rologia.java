package net.geforce.smartwatch.gui.rologia;

import net.geforce.smartwatch.gui.rologia.screens.BootScreen;
import net.geforce.smartwatch.gui.rologia.screens.Screen;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.minecraft.entity.player.EntityPlayer;

public class Rologia {
	
	public static final String VERSION = "0.0.1";
	
	private Screen currentScreen;

	private EntityPlayer user;
	
	public void openSmartwatchGUI(EntityPlayer player, int screenXPos, int screenYPos) {
		user = player;
		setScreen(new BootScreen(screenXPos, screenYPos));
		currentScreen.initializeScreen();
	}

	public void renderScreen(int mouseX, int mouseY) {
		if(currentScreen.getMouseX() != mouseX || currentScreen.getMouseY() != mouseY)
			currentScreen.setMousePos(mouseX, mouseY);
			
		currentScreen.editImages();
		currentScreen.drawImages();
		
		currentScreen.editComponents();
		currentScreen.drawComponents();
	}
	
	public static Rologia getInstanceFromWatch(ItemMineWatch watch) {
		return new Rologia();
	}
	
	public EntityPlayer getUser() {
		return user;
	}
	
	public void setScreen(Screen newScreen) {
		currentScreen = newScreen;
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}

}
