package net.geforcemods.smartwatch.gui;

import net.geforcemods.smartwatch.item.ItemMineWatch;
import net.geforcemods.smartwatch.resources.ResourceLoader;
import net.geforcemods.smartwatch.rologia.gui.screens.Screen;
import net.geforcemods.smartwatch.rologia.os.Rologia;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMineWatch extends GuiScreen {
	
	private static final ResourceLocation SCREEN_EDGE_TEXTURE = new ResourceLocation(ResourceLoader.TEXTURE_FOLDER_PATH + "watch_screen_background_2.png");

	private EntityPlayer player;
	
	private Rologia rologia;
	private ItemMineWatch mineWatch;

	public GuiMineWatch(EntityPlayer playerWhoOpenedGUI, ItemMineWatch watch) {
		player = playerWhoOpenedGUI;
		mineWatch = watch;
		rologia = Rologia.getInstanceForPlayer(playerWhoOpenedGUI);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		rologia.openSmartwatchGUI(player, (width - Screen.WATCH_SCREEN_X_SIZE) / 2, (height - Screen.WATCH_SCREEN_Y_SIZE) / 2);
	}
	
	@Override
	public void updateScreen() {
		rologia.update();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		mc.getTextureManager().bindTexture(SCREEN_EDGE_TEXTURE);
		int startX = (width / 2) - (Screen.WATCH_BACKGROUND_X_SIZE / 2);
		int startY = (height / 2) - (Screen.WATCH_BACKGROUND_Y_SIZE / 2);
		drawTexturedModalRect(startX, startY, 0, 0, Screen.WATCH_BACKGROUND_X_SIZE, Screen.WATCH_BACKGROUND_Y_SIZE);
		rologia.renderScreen(mouseX, mouseY);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int buttonClicked) {
		Screen screen = rologia.getCurrentScreen();
		screen.handleMouseClick(mouseX, mouseY, buttonClicked);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
