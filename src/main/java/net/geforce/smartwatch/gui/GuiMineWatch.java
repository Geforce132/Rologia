package net.geforce.smartwatch.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.geforce.smartwatch.gui.rologia.framework.Rologia;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class GuiMineWatch extends GuiScreen {
	
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
		
		rologia.openSmartwatchGUI(player);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		rologia.renderScreen(mouseX, mouseY);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int buttonClicked) {
		rologia.getCurrentScreen().handleMouseClick(mouseX, mouseY, buttonClicked);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}
