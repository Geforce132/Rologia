package net.geforce.smartwatch.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.geforce.smartwatch.MineWatch;
import net.geforce.smartwatch.containers.ContainerEmpty;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.geforce.smartwatch.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	public static final int BOOT_SCREEN_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID)
		{
		case BOOT_SCREEN_ID:
			return new ContainerEmpty();
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID)
		{
		case BOOT_SCREEN_ID:
			if(!PlayerUtils.isHoldingItem(player, MineWatch.mineWatch)) return null;
			
			return new GuiMineWatch(player, (ItemMineWatch) player.getCurrentEquippedItem().getItem());
		}
		
		return null;
	}

}
