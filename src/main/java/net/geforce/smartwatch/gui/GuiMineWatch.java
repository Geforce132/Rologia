package net.geforce.smartwatch.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.geforce.smartwatch.gui.rologia.Rologia;
import net.geforce.smartwatch.gui.rologia.screens.Screen;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiMineWatch extends GuiScreen {
	
	private static final ResourceLocation SCREEN_EDGE_TEXTURE = new ResourceLocation("rologia:textures/gui/watch/watch_screen_background_2.png");

	private EntityPlayer player;
	
	private Rologia rologia;
	private ItemMineWatch mineWatch;

	public GuiMineWatch(EntityPlayer playerWhoOpenedGUI, ItemMineWatch watch) {
		player = playerWhoOpenedGUI;
		mineWatch = watch;
		rologia = Rologia.getInstanceFromWatch(mineWatch);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		rologia.openSmartwatchGUI(player, (width - Screen.WATCH_SCREEN_X_SIZE) / 2, (height - Screen.WATCH_SCREEN_Y_SIZE) / 2);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		mc.getTextureManager().bindTexture(SCREEN_EDGE_TEXTURE);
		int startX = (width - Screen.WATCH_SCREEN_X_SIZE) / 2;
		int startY = (height - Screen.WATCH_SCREEN_Y_SIZE) / 2;
		drawTexturedModalRect(startX, startY, 0, 0, Screen.WATCH_BACKGROUND_X_SIZE, Screen.WATCH_BACKGROUND_Y_SIZE);
		
		rologia.renderScreen(mouseX, mouseY);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int buttonClicked) {
		Screen screen = rologia.getCurrentScreen();
		//screen.handleMouseClick((mouseX - screen.getXPos()) - 3, (mouseY - screen.getYPos()) - 3, buttonClicked);
		screen.handleMouseClick(mouseX, mouseY, buttonClicked);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
